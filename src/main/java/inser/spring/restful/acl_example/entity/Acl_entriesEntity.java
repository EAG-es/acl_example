package inser.spring.restful.acl_example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(Acl_entriesId.class)
@Table(name = "acl_entries")
public class Acl_entriesEntity {
    public static final String k_entry_permit_separator = ",";
    public static final String k_entry_permit_read = "read";
    public static final String k_entry_permit_write = "write";
    public static final String k_entry_permit_execute = "execute";
    public static final String k_entry_permit_create = "create";
    public static final String k_entry_permit_delete = "delete";
    public static final String k_entry_permit_send = "send";
    public static final String k_entry_permit_accept = "accept";
    public static final String k_entry_permit_grant = "grant";
    public static final String k_entry_permit_browse = k_entry_permit_read;
    public static final String k_entry_permit_operate_rw = k_entry_permit_read
            + k_entry_permit_separator
            + k_entry_permit_write;
    public static final String k_entry_permit_operate_rwx = k_entry_permit_operate_rw
            + k_entry_permit_separator
            + k_entry_permit_execute;
    public static final String k_entry_permit_operate_crwd = k_entry_permit_operate_rw
            + k_entry_permit_separator
            + k_entry_permit_create
            + k_entry_permit_separator
            + k_entry_permit_delete;
    public static final String k_entry_permit_operate_crwdx = k_entry_permit_operate_crwd
            + k_entry_permit_execute;
    public static final String k_entry_permit_operate_admin = k_entry_permit_operate_crwdx
            + k_entry_permit_grant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_acl_class", referencedColumnName="id_acl_class")
    @JoinColumn(name = "id_object_csv", referencedColumnName="id_object_csv")
    @JoinColumn(name = "id_list_order", referencedColumnName="id_list_order")
    private Acl_entriesEntity id;

    @Id
    private String id_acl_class;

    @Id
    private String id_object_csv;

    @Id
    private Integer id_list_order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ref_acl_actor", nullable = false)
    private Acl_actorsEntity ref_acl_actor;

    @Column(name = "permits_csv", nullable = false)
    private String permits_csv;

    @Column(name = "granting_from_parent", nullable = false)
    private Boolean granting_from_parent = false;

}