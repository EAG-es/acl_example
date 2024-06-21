package inser.spring.restful.acl_example.service;

import innui.modelos.errors.Oks;
import inser.spring.restful.acl_example.repository.*;
import inser.spring.restful.acl_example.entity.Acl_actorsEntity;
import static inser.spring.restful.acl_example.entity.Acl_actorsEntity.k_actor_type_group;
import static inser.spring.restful.acl_example.entity.Acl_actorsEntity.k_actor_type_principal;
import inser.spring.restful.acl_example.entity.Acl_class_objectsEntity;
import inser.spring.restful.acl_example.entity.Acl_class_objectsId;
import inser.spring.restful.acl_example.entity.Acl_classesEntity;
import java.io.File;
import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author emilio
 */
@Service
public class AclService extends inser.spring.restful.acl_example.repository.AclRepositoryService {
    // Properties file for translactions
    public static String k_in_route;
    static {
        String paquete_tex = AclService.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_route = "in/" + paquete_tex + "/in";
    }

//    @Autowired
//    private DataSource dataSource;

    public AclService(AclRepository aclRepository) throws Exception {
        super(aclRepository);
    }

    public Boolean is_class_objects(Acl_class_objectsId id) throws Exception {
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                Optional<Acl_class_objectsEntity> acl_class_objectsEntity_optional;
                acl_class_objectsEntity_optional = aclRepository.acl_class_objectsRepository.findById(id);
                return acl_class_objectsEntity_optional.isPresent();
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return false;
    }

    public Collection<Acl_actorsEntity> get_all_actors() throws Exception {
        Collection<Acl_actorsEntity> retorno = null;
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                retorno = aclRepository.acl_actorsRepository.findAll();
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return retorno;
    }

    public Collection<Acl_actorsEntity> get_actors() throws Exception {
        return get_actors(k_actor_type_principal);
    }

    public Collection<Acl_actorsEntity> get_groups() throws Exception {
        return get_actors(k_actor_type_group);
    }

    public boolean is_any_actor(String id_acl_actor) throws Exception {
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                Optional<Acl_actorsEntity> acl_actorsEntity_optional;
                acl_actorsEntity_optional = aclRepository.acl_actorsRepository.findById(id_acl_actor);
                return acl_actorsEntity_optional.isPresent();
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return false;
    }

    public boolean is_actor(int actor_type) throws Exception {
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                Optional<Acl_actorsEntity> acl_actorsEntity_optional;
                acl_actorsEntity_optional = aclRepository.acl_actorsRepository.findById(this.acl_actorsEntity.getId_acl_actor());
                if (acl_actorsEntity_optional.isPresent()) {
                    return (acl_actorsEntity_optional.get().getPrincipal_type() == actor_type);
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

    public boolean is_actor() throws Exception {
        return is_actor(k_actor_type_principal);
    }

    public boolean is_group() throws Exception {
        return is_actor(k_actor_type_group);
    }
    /**
     * Search recursively to know if is grouped in an actor_group
     * @param id_acl_actor_group
     * @return
     * @throws Exception
     */
    public boolean is_in_actor_groups(String id_acl_actor_group) throws Exception {
        return _is_actor_in_actor_groups(acl_actorsEntity.getId_acl_actor(), id_acl_actor_group);
    }

    public Collection<Acl_classesEntity> get_classes() throws Exception {
        Collection<Acl_classesEntity> retorno = null;
        Oks ok = new Oks();
//        ResourceBundle in = null;
        do {
            try {
//                in = ResourceBundles.getBundle(k_in_route);
                retorno = aclRepository.acl_classesRepository.findAll();
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            throw new Exception(ok.txt);
        }
        return retorno;
    }

}
