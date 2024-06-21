package inser.spring.restful.acl_example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
public class Acl_entriesId implements java.io.Serializable {
    private static final long serialVersionUID = 4671862022257714384L;
    @Column(name = "id_acl_class", nullable = false)
    private String id_acl_class;

    @Column(name = "id_object_csv", nullable = false)
    private String id_object_csv;

    @Column(name = "id_list_order", nullable = false)
    private Integer id_list_order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Acl_entriesId entity = (Acl_entriesId) o;
        return Objects.equals(this.id_list_order, entity.id_list_order) &&
                Objects.equals(this.id_acl_class, entity.id_acl_class) &&
                Objects.equals(this.id_object_csv, entity.id_object_csv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_list_order, id_acl_class, id_object_csv);
    }

}