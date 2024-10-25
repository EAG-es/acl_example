package inser.spring.restful.acl_example.repository;

import inser.spring.restful.acl_example.entity.Acl_actorsEntity;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emilio
 */
interface Acl_actorsRepository extends JpaRepository<Acl_actorsEntity, String> {

    @Transactional(rollbackFor = Exception.class)
    @Query("select a " +
            "from Acl_actorsEntity a where "
            + "a.principal_type = :principal_type ")
    Collection<Acl_actorsEntity> findAllByType(Integer principal_type);

}
