package inser.spring.restful.acl_example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(Acl_actor_groupsId.class)
@Table(name = "acl_actor_groups")
public class Acl_actor_groupsEntity {
//    @EmbeddedId
//    private Acl_actor_groupsId id;

    @Id
    private String id_acl_actor_grouped;

    @Id
    private String id_acl_actor_group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_actor_grouped", referencedColumnName = "id_acl_actor", nullable = false)
    private Acl_actorsEntity ref_acl_actor_grouped;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_actor_group", referencedColumnName = "id_acl_actor", nullable = false)
    private Acl_actorsEntity ref_acl_actor_group;


}