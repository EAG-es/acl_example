package inser.spring.restful.acl_example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(Acl_class_objectsId.class)
@Table(name = "acl_class_objects")
public class Acl_class_objectsEntity {

    @Id
    private String id_acl_class;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_class", nullable = false)
    private Acl_classesEntity reg_id_acl_class;

    @Id
    private String id_object_csv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_parent_class", referencedColumnName="id_acl_class")
    @JoinColumn(name = "ref_parent_object_csv", referencedColumnName="id_object_csv")
    private Acl_class_objectsEntity ref_parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_owner_acl_actor")
    private Acl_actorsEntity ref_owner_acl_actor;

}