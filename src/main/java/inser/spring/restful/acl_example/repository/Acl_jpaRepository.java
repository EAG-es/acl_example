package inser.spring.restful.acl_example.repository;

import innui.modelos.errors.Oks;
import inser.spring.restful.acl_example.entity.Acl_actorsEntity;
import inser.spring.restful.acl_example.entity.Acl_class_objectsId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static inser.spring.restful.acl_example.entity.Acl_entriesEntity.k_entry_permit_delete;
import static inser.spring.restful.acl_example.entity.Acl_entriesEntity.k_entry_permit_write;

/**
 *
 * @author emilio
 * @param <R>
 * @param <T>
 * @param <ID>
 */
public class Acl_jpaRepository<R extends JpaRepository<T, ID>, T extends Object, ID extends Object> implements JpaRepository<T, ID> {
    // Properties file for translactions
    public static String k_in_route;
    static {
        String paquete_tex = Acl_jpaRepository.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_route = "in/" + paquete_tex + "/in";
    }
    public static String k_acl_jpaRepository_group = "jpaRepository_group";
    public static String k_acl_jpaRepository_admin = "jpaRepository_admin";
    public static String k_acl_jpaRepository_write = "jpaRepository_write";
    public static String k_acl_jpaRepository_id_class = "inser.spring.restful.acl_example.repository.Acl_jpaRepository";
    public static String k_acl_jpaRepository_id_object = "acl_jpaRepository";
    static Acl_actorsEntity required_group = new Acl_actorsEntity() {
        {
            this.setId_acl_actor(k_acl_jpaRepository_group);
        }
    };

    @Setter
    @Getter
    R jpaRepository;
    AclBase aclBase;
    Acl_class_objectsId acl_class_objectsId;

    public static class AclOperationNotAllowed extends RuntimeException {
        public AclOperationNotAllowed () {
            super();
        }
        public AclOperationNotAllowed(String message) {
            super(message);
        }
        public AclOperationNotAllowed(Throwable cause) {
            super(cause);
        }
    }
    public Acl_jpaRepository(R jpaRepository, AclBase aclService) throws Exception {
        Oks ok = new Oks();
        init(jpaRepository, aclService, ok);
        if (ok.is == false) {
            throw new Exception(ok.getTxt());
        }
    }

    public boolean init(R jpaRepository, AclBase aclService, Oks ok, Object ... extras_array) throws Exception {
        if (ok.is == false) { return false; }
        try {
            this.jpaRepository = jpaRepository;
            this.aclBase = aclService;
            acl_class_objectsId = new Acl_class_objectsId();
            acl_class_objectsId.setId_acl_class(k_acl_jpaRepository_id_class);
            acl_class_objectsId.setId_object_csv(k_acl_jpaRepository_id_object);
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.is;
    }

    @Override
    public void flush() {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_write)) {
                jpaRepository.flush();
            } else {
                throw new AclOperationNotAllowed("flush");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_write)) {
                return jpaRepository.saveAndFlush(entity);
            } else {
                throw new AclOperationNotAllowed("saveAndFlush");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_write)) {
                return jpaRepository.saveAllAndFlush(entities);
            } else {
                throw new AclOperationNotAllowed("saveAllAndFlush");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAllInBatch(entities);
            } else {
                throw new AclOperationNotAllowed("deleteAllInBatch");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAllByIdInBatch(ids);
            } else {
                throw new AclOperationNotAllowed("deleteAllByIdInBatch");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAllInBatch() {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAllInBatch();
            } else {
                throw new AclOperationNotAllowed("deleteAllInBatch");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public T getOne(ID id) {
        return jpaRepository.getOne(id);
    }

    @Override
    public T getById(ID id) {
        return jpaRepository.getById(id);
    }

    @Override
    public T getReferenceById(ID id) {
        return jpaRepository.getReferenceById(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return jpaRepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return jpaRepository.findAll(example, sort);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_write)) {
                return jpaRepository.saveAll(entities);
            } else {
                throw new AclOperationNotAllowed("saveAll");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return jpaRepository.findAllById(ids);
    }

    @Override
    public <S extends T> S save(S entity) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_write)) {
                return jpaRepository.save(entity);
            } else {
                throw new AclOperationNotAllowed("save");
            }
        } catch (AclOperationNotAllowed u) {
            throw u;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public void deleteById(ID id) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteById(id);
            } else {
                throw new AclOperationNotAllowed("deleteById");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void delete(T entity) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.delete(entity);
            } else {
                throw new AclOperationNotAllowed("delete");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAllById(ids);
            } else {
                throw new AclOperationNotAllowed("deleteAllById");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAll(entities);
            } else {
                throw new AclOperationNotAllowed("deleteAll");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            if (aclBase.hasPermission(aclBase.acl_actorsEntity_to_check, required_group, acl_class_objectsId, k_entry_permit_delete)) {
                jpaRepository.deleteAll();
            } else {
                throw new AclOperationNotAllowed("deleteAll");
            }
        } catch (AclOperationNotAllowed e) {
            throw e;
        } catch (Exception e) {
            throw new AclOperationNotAllowed(e);
        }
    }

    @Override
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return jpaRepository.findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return jpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return jpaRepository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return jpaRepository.exists(example);
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return jpaRepository.findBy(example, queryFunction);
    }

}
