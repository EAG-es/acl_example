package inser.spring.restful.acl_example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
public class Acl_class_objectsId implements java.io.Serializable {
    private static final long serialVersionUID = -325596479739330273L;
    @Column(name = "id_acl_class", nullable = false)
    private String id_acl_class;

    @Column(name = "id_object_csv", nullable = false)
    private String id_object_csv;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Acl_class_objectsId entity = (Acl_class_objectsId) o;
        return Objects.equals(this.id_acl_class, entity.id_acl_class) &&
                Objects.equals(this.id_object_csv, entity.id_object_csv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_acl_class, id_object_csv);
    }

}