package inser.spring.restful.acl_example.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;


@Getter
@Setter
public class Acl_actor_groupsId implements java.io.Serializable {
    private static final long serialVersionUID = -3023170277810386272L;
    @Column(name = "id_acl_actor_grouped", nullable = false)
    private String id_acl_actor_grouped;

    @Column(name = "id_acl_actor_group", nullable = false)
    private String id_acl_actor_group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Acl_actor_groupsId entity = (Acl_actor_groupsId) o;
        return Objects.equals(this.id_acl_actor_group, entity.id_acl_actor_group) &&
                Objects.equals(this.id_acl_actor_grouped, entity.id_acl_actor_grouped);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_acl_actor_group, id_acl_actor_grouped);
    }

}