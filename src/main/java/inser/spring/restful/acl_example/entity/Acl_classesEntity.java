package inser.spring.restful.acl_example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acl_classes")
public class Acl_classesEntity {
    @Id
    @Column(name = "id_acl_class", nullable = false)
    private String id_acl_class;

}