INSERT INTO acl_example.acl_actors (id_acl_actor,principal_type) VALUES
	 ('jpaRepository_group',1),
	 ('jpaRepository_admin',0),
	 ('jpaRepository_write',0);

INSERT INTO acl_example.acl_actor_groups
(id_acl_actor_grouped, id_acl_actor_group)
VALUES('jpaRepository_admin', 'jpaRepository_group');

INSERT INTO acl_example.acl_classes (id_acl_class) VALUES
	 ('inser.spring.restful.acl_example.repository.Acl_jpaRepository');

INSERT INTO acl_example.acl_class_objects 
        (id_acl_class,id_object_csv,ref_parent_class,ref_parent_object_csv,ref_owner_acl_actor)
VALUES
# Diferent object ids for the same class
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','acl_jpaRepository',NULL,NULL,'jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','write',NULL,NULL,'jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','read',NULL,NULL,'jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_rw','inser.spring.restful.acl_example.repository.Acl_jpaRepository','read','jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_crwd','inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_rw','jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_crwdx','inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_crwd','jpaRepository_group'),
        ('inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_admin','inser.spring.restful.acl_example.repository.Acl_jpaRepository','operate_crwdx','jpaRepository_group');

INSERT INTO acl_example.acl_entries
    (id_acl_class, id_object_csv, id_list_order, ref_acl_actor, permits_csv, granting_from_parent)
VALUES
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'write', 0, 'jpaRepository_write', 'write', 0),
# Using inheritance
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'read', 0, 'jpaRepository_group', 'read', 0),
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'operate_rw', 1, 'jpaRepository_group', 'read,write', 0),
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'operate_crwd', 2, 'jpaRepository_group', 'create,delete', 1),
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'operate_crwdx', 3, 'jpaRepository_group', 'execute', 1),
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'operate_admin', 4, 'jpaRepository_group', 'grant', 1),
# Not using inheritance
    ('inser.spring.restful.acl_example.repository.Acl_jpaRepository', 'acl_jpaRepository', 5, 'jpaRepository_group', 'read,write,create,delete,execute,send,accept,grant', 0);
