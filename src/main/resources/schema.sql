CREATE TABLE IF NOT EXISTS `acl_classes` (
  `id_acl_class` varchar(255) NOT NULL,
  PRIMARY KEY (`id_acl_class`)
);

CREATE TABLE IF NOT EXISTS `acl_actors` (
  `id_acl_actor` varchar(255) NOT NULL,
  `principal_type` int NOT NULL,
  PRIMARY KEY (`id_acl_actor`)
);

CREATE TABLE IF NOT EXISTS `acl_actor_groups` (
  `id_acl_actor_grouped` varchar(255) NOT NULL,
  `id_acl_actor_group` varchar(255) NOT NULL,
  PRIMARY KEY (`id_acl_actor_grouped`, `id_acl_actor_group`),
  KEY `fk_acl_actor_groups__acl_actors_grouped` (`id_acl_actor_grouped`),
  KEY `fk_acl_actor_groups__acl_actors_group` (`id_acl_actor_group`),
  CONSTRAINT `fk_acl_actor_groups__acl_actors_grouped` FOREIGN KEY (`id_acl_actor_grouped`) REFERENCES `acl_actors` (`id_acl_actor`),
  CONSTRAINT `fk_acl_actor_groups__acl_actors_group` FOREIGN KEY (`id_acl_actor_group`) REFERENCES `acl_actors` (`id_acl_actor`)
);

CREATE TABLE IF NOT EXISTS `acl_class_objects` (
  `id_acl_class` varchar(255) NOT NULL,
  `id_object_csv` varchar(255) NOT NULL,
  `ref_parent_class` varchar(255) DEFAULT NULL,
  `ref_parent_object_csv` varchar(255) DEFAULT NULL,
  `ref_owner_acl_actor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_acl_class`, `id_object_csv`),
  KEY `fk_acl_class_objects__acl_class_objects` (`ref_parent_class`, `ref_parent_object_csv`),
  KEY `fk_acl_class_objects__acl_actors` (`ref_owner_acl_actor`),
  KEY `fk_acl_class_objects__acl_clases` (`id_acl_class`),
  CONSTRAINT `fk_acl_class_objects__acl_class_objects` FOREIGN KEY (`ref_parent_class`, `ref_parent_object_csv`) REFERENCES `acl_class_objects` (`id_acl_class`, `id_object_csv`),
  CONSTRAINT `fk_acl_class_objects__acl_actors` FOREIGN KEY (`ref_owner_acl_actor`) REFERENCES `acl_actors` (`id_acl_actor`),
  CONSTRAINT `fk_acl_class_objects__acl_classes` FOREIGN KEY (`id_acl_class`) REFERENCES `acl_classes` (`id_acl_class`)
);

CREATE TABLE IF NOT EXISTS `acl_entries` (
  `id_acl_class` varchar(255) NOT NULL,
  `id_object_csv` varchar(255) NOT NULL,
  `id_list_order` int NOT NULL,
  `ref_acl_actor` varchar(255) NOT NULL,
  `permits_csv` varchar(255) NOT NULL,
  `granting_from_parent` boolean NOT NULL,
  PRIMARY KEY (`id_acl_class`, `id_object_csv`, `id_list_order`),
  KEY `fk_acl_entries__acl_class_objects` (`id_acl_class`, `id_object_csv`),
  KEY `fk_acl_entries__acl_actors` (`ref_acl_actor`),
  CONSTRAINT `fk_acl_entries__acl_class_objects` FOREIGN KEY (`id_acl_class`, `id_object_csv`) REFERENCES `acl_class_objects` (`id_acl_class`, `id_object_csv`),
  CONSTRAINT `fk_acl_entries__acl_actors` FOREIGN KEY (`ref_acl_actor`) REFERENCES `acl_actors` (`id_acl_actor`)
);