package inser.spring.restful.acl_example.repository;

import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import inser.spring.restful.acl_example.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.*;

/**
 *
 * @author emilio
 */
public class AclBase {
    // Properties file for translactions
    public static String k_in_route;
    static {
        String paquete_tex = AclBase.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_route = "in/" + paquete_tex + "/in";
    }

//    @Autowired
//    private DataSource dataSource;

    @Setter
    @Getter
    public Acl_actorsEntity acl_actorsEntity_to_check;

    public final AclRepository aclRepository;

    public AclBase(AclRepository aclRepository) throws Exception {
        this.aclRepository = aclRepository;
        aclRepository.init(this);
    }

    /**
     * search for the ok permits in acl for the class_object or its parents (if granting_from_parent is true)
     * @param id_acl_actor_or_group
     * @param acl_class_objectsId
     * @param ok_permits_csv
     * @return True if its is permitted, false if no permit found.
     * @throws Exception
     */
    public Boolean hasPermission(Acl_actorsEntity id_acl_actor_or_group, Acl_class_objectsId acl_class_objectsId, String ok_permits_csv) throws Exception {
        Boolean retorno = null;
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                Collection<Acl_entriesEntity> Acl_entriesEntities_collection;
                Acl_entriesRepository acl_entriesRepository = aclRepository.acl_entriesRepository.jpaRepository;
                Acl_entriesEntities_collection = acl_entriesRepository.findByClass_and_object_csv(acl_class_objectsId.getId_acl_class()
                                , acl_class_objectsId.getId_object_csv()
                                , id_acl_actor_or_group);
                if (Acl_entriesEntities_collection.isEmpty() == false) {
                    String [] permits_acl_array;
                    permits_acl_array = ok_permits_csv.split(",");
                    for (String permit: permits_acl_array) {
                        permit = permit.trim().toLowerCase();
                    }
                    List<String> permits_acl_list;
                    permits_acl_list = Arrays.asList(permits_acl_array);
                    String permits_csv;
                    String [] permits_array;
                    for (Acl_entriesEntity item: Acl_entriesEntities_collection) {
                        permits_csv = item.getPermits_csv();
                        permits_array = permits_csv.split(",");
                        for (String permit: permits_array) {
                            permit = permit.trim().toLowerCase();
                            if (permits_acl_list.contains(permit)) {
                                return true;
                            }
                        }
                        if (item.getGranting_from_parent()) {
                            Acl_class_objectsId parenty_acl_class_objectsId;
                            parenty_acl_class_objectsId = new Acl_class_objectsId();
                            parenty_acl_class_objectsId.setId_acl_class(item.getId_acl_class());
                            parenty_acl_class_objectsId.setId_object_csv(item.getId_object_csv());
                            Optional<Acl_class_objectsEntity> parenty_acl_class_objectsEntity_optional;
                            parenty_acl_class_objectsEntity_optional = aclRepository.acl_class_objectsRepository.findById(parenty_acl_class_objectsId);
                            if (parenty_acl_class_objectsEntity_optional.isPresent()) {
                                Acl_class_objectsEntity parenty_acl_class_objectsEntity;
                                parenty_acl_class_objectsEntity = parenty_acl_class_objectsEntity_optional.get();
                                Acl_class_objectsEntity acl_class_objectsEntity = parenty_acl_class_objectsEntity.getRef_parent();
                                Acl_class_objectsId new_acl_class_objectsId = new Acl_class_objectsId();
                                new_acl_class_objectsId.setId_acl_class(acl_class_objectsEntity.getId_acl_class());
                                new_acl_class_objectsId.setId_object_csv(acl_class_objectsEntity.getId_object_csv());
                                retorno = hasPermission(id_acl_actor_or_group, new_acl_class_objectsId, ok_permits_csv);
                                if (retorno) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return false;
    }
    /**
     * search for the ok permits in acl for the class_object or its parents (if granting_from_parent is true)
     * @param acl_class_objectsId
     * @param ok_permits_csv
     * @return True if its is permitted, false if no permit found.
     * @throws Exception
     */
    public Boolean hasPermission(Acl_class_objectsId acl_class_objectsId, String ok_permits_csv) throws Exception {
        Oks ok = new Oks();
        ResourceBundle in = null;
        in = ResourceBundles.getBundle(k_in_route);
        if (acl_actorsEntity_to_check == null) {
            ok.setTxt(Tr.in(in, "Not defined acl_actor entity"));
            return false;
        }
        return hasPermission(acl_actorsEntity_to_check, acl_class_objectsId, ok_permits_csv);

    }
    /**
     * Combination of is_in_group + hasPermission
     * search for the ok permits in acl for the class_object or its parents (if granting_from_parent is true)
     * @param id_acl_actor Actor to check permissions
     * @param id_acl_group Group to check permissions whose actor belong to the group (can be null)
     * @param acl_class_objectsId
     * @param ok_permits_csv
     * @return True if its is permitted, false if no permit found.
     * @throws Exception
     */
    public Boolean hasPermission(Acl_actorsEntity id_acl_actor, Acl_actorsEntity id_acl_group, Acl_class_objectsId acl_class_objectsId, String ok_permits_csv) throws Exception {
        if (hasPermission(id_acl_actor, acl_class_objectsId, ok_permits_csv)) {
            return true;
        } else if (id_acl_group != null) {
            if (is_actor_in_group(id_acl_actor, id_acl_group)) {
                return hasPermission(id_acl_group, acl_class_objectsId, ok_permits_csv);
            }
        }
        return false;
    }

    public Collection<Acl_class_objectsEntity> get_class_objects_from_class(String id_acl_class) throws Exception {
        Collection<Acl_class_objectsEntity> retorno = null;
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                retorno = aclRepository.acl_class_objectsRepository.getJpaRepository().findByClass(id_acl_class);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return retorno;
    }
    public Collection<Acl_actorsEntity> get_actors(int actor_type) throws Exception {
        Collection<Acl_actorsEntity> retorno = null;
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                retorno = aclRepository.acl_actorsRepository.getJpaRepository().findAllByType(actor_type);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return retorno;
    }

    /**
     * Search recursively for an actor to know if is be grouped in an actor_group
     * @param id_acl_actor
     * @param id_acl_group
     * @return
     * @throws Exception
     */
    public boolean is_actor_in_group(Acl_actorsEntity id_acl_actor, Acl_actorsEntity id_acl_group) throws Exception {
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                String group_lower = id_acl_group.getId_acl_actor().toLowerCase();
                if (id_acl_actor.getId_acl_actor().toLowerCase().equals(group_lower)) {
                    return true;
                }
                Collection<Acl_actor_groupsEntity> acl_actor_groupsEntity_collection;
                acl_actor_groupsEntity_collection = aclRepository.acl_actor_groupsRepository.getJpaRepository().findByGrouped(id_acl_actor.getId_acl_actor());
                Acl_actorsEntity next_to_check_acl_actorsEntity;
                String next_to_check_lower;
                for (var item: acl_actor_groupsEntity_collection) {
                    next_to_check_lower = item.getId_acl_actor_group().toLowerCase();
                    if (next_to_check_lower.equals(group_lower)) {
                        return true;
                    } else {
                        next_to_check_acl_actorsEntity = new Acl_actorsEntity();
                        next_to_check_acl_actorsEntity.setId_acl_actor(next_to_check_lower);
                        if (is_actor_in_group(next_to_check_acl_actorsEntity, id_acl_group)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return false;
    }

    public boolean is_class(String id_acl_class) throws Exception {
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                Optional<Acl_classesEntity> acl_classesEntity_optional;
                acl_classesEntity_optional = aclRepository.acl_classesRepository.getJpaRepository().findById(id_acl_class);
                return acl_classesEntity_optional.isPresent();
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return false;
    }

}
