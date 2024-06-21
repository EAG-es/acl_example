package inser.spring.restful.acl_example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acl_actor_groups")
public class Acl_actor_groupsEntity {
    @EmbeddedId
    private Acl_actor_groupsId id;

    @MapsId("idAclActorGrouped")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_actor_grouped", nullable = false)
    private Acl_actorsEntity id_acl_actor_grouped;

    @MapsId("idAclActorGroup")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_actor_group", nullable = false)
    private Acl_actorsEntity id_acl_actor_group;

}