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
@Table(name = "acl_actors")
public class Acl_actorsEntity {
    public static final int k_actor_type_principal = 0;
    public static final int k_actor_type_group = 1;

    @Id
    @Column(name = "id_acl_actor", nullable = false)
    private String id_acl_actor;

    @Column(name = "principal_type", nullable = false)
    private Integer principal_type;

}