package inser.spring.restful.acl_example.repository;

import inser.spring.restful.acl_example.entity.Acl_actorsEntity;
import inser.spring.restful.acl_example.entity.Acl_entriesEntity;
import inser.spring.restful.acl_example.entity.Acl_entriesId;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emilio
 */
interface Acl_entriesRepository extends JpaRepository<Acl_entriesEntity, Acl_entriesId> {

    @Transactional(rollbackFor = Exception.class)
    @Query("select a " +
            "from Acl_entriesEntity a where "
            + "a.id_acl_class = :id_acl_class "
            + "and "
            + "a.id_object_csv = :id_object_csv "
            + "and "
            + "a.ref_acl_actor = :id_acl_actor "
            + "order by a.id_list_order"
            )
    Collection<Acl_entriesEntity> findByClass_and_object_csv(String id_acl_class, String id_object_csv, Acl_actorsEntity id_acl_actor);
}
