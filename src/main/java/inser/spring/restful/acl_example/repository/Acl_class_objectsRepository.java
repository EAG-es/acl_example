package inser.spring.restful.acl_example.repository;

import inser.spring.restful.acl_example.entity.Acl_class_objectsEntity;
import inser.spring.restful.acl_example.entity.Acl_class_objectsId;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emilio
 */
interface Acl_class_objectsRepository extends JpaRepository<Acl_class_objectsEntity, Acl_class_objectsId> {

    @Transactional(rollbackFor = Exception.class)
    @Query("select a " +
            "from Acl_class_objectsEntity a where "
            + "a.id_acl_class = :id_acl_class")
    Collection<Acl_class_objectsEntity> findByClass(String id_acl_class);

}
