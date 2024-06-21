package inser.spring.restful.acl_example.repository;

import inser.spring.restful.acl_example.entity.Acl_actor_groupsEntity;
import inser.spring.restful.acl_example.entity.Acl_actor_groupsId;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emilio
 */
interface Acl_actor_groupsRepository extends JpaRepository<Acl_actor_groupsEntity, Acl_actor_groupsId> {

    @Transactional(rollbackFor = Exception.class)
    @Query("select a " +
            "from Acl_actor_groupsEntity a where "
            + "a.id_acl_actor_grouped = :actor_grouped ")
    Collection<Acl_actor_groupsEntity> findByGrouped(String actor_grouped);

}
