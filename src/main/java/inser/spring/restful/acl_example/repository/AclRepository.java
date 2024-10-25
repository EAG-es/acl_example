package inser.spring.restful.acl_example.repository;

import inser.spring.restful.acl_example.entity.*;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 *
 * @author emilio
 */
@Component
public class AclRepository {
    // Properties file for translactions
    public static String k_in_route;
    static {
        String paquete_tex = AclRepository.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_route = "in/" + paquete_tex + "/in";
    }

    @Getter
    public final Acl_jpaRepository<Acl_class_objectsRepository, Acl_class_objectsEntity, Acl_class_objectsId> acl_class_objectsRepository;
    @Getter
    public final Acl_jpaRepository<Acl_entriesRepository, Acl_entriesEntity, Acl_entriesId> acl_entriesRepository;
    @Getter
    public final Acl_jpaRepository<Acl_classesRepository, Acl_classesEntity, String> acl_classesRepository;
    @Getter
    public final Acl_jpaRepository<Acl_actorsRepository, Acl_actorsEntity, String> acl_actorsRepository;
    @Getter
    public final Acl_jpaRepository<Acl_actor_groupsRepository, Acl_actor_groupsEntity, Acl_actor_groupsId> acl_actor_groupsRepository;
    @Getter
    @Setter
    public AclBase aclBase = null;

    public AclRepository(Acl_class_objectsRepository acl_class_objectsRepository
      , Acl_classesRepository acl_classesRepository
      , Acl_entriesRepository acl_entriesRepository
      , Acl_actorsRepository acl_actorsRepository
      , Acl_actor_groupsRepository acl_actor_groupsRepository) throws Exception {
        this.acl_class_objectsRepository = new Acl_jpaRepository<>(acl_class_objectsRepository, aclBase);
        this.acl_classesRepository = new Acl_jpaRepository<>(acl_classesRepository, aclBase);
        this.acl_entriesRepository = new Acl_jpaRepository<>(acl_entriesRepository, aclBase);
        this.acl_actorsRepository = new Acl_jpaRepository<>( acl_actorsRepository, aclBase);
        this.acl_actor_groupsRepository = new Acl_jpaRepository<>(acl_actor_groupsRepository, aclBase);
    }

    /**
     * Completes the process of creating an AclRepository object
     * @param aclBase
     * @return
     */
    public boolean init(AclBase aclBase) {
        this.aclBase = aclBase;
        acl_class_objectsRepository.aclBase = aclBase;
        acl_classesRepository.aclBase = aclBase;
        acl_entriesRepository.aclBase = aclBase;
        acl_actorsRepository.aclBase = aclBase;
        acl_actor_groupsRepository.aclBase = aclBase;
        return true;
    }

}
