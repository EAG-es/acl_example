package inser.spring.restful.acl_example.controller;

import innui.modelos.errors.Oks;
import inser.spring.restful.acl_example.entity.Acl_actorsEntity;

import inser.spring.restful.acl_example.service.AclService;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static inser.spring.restful.acl_example.repository.Acl_jpaRepository.*;

/**
 *
 * @author emilio
 */
@RestController
@RequestMapping("/acl_example")
public class Acl_exampleController {
    // Properties file for translactions
    public static String k_in_route;
    static {
        String paquete_tex = Acl_exampleController.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_route = "in/" + paquete_tex + "/in";
    }

    public final AclService aclService;

    public Acl_exampleController(AclService aclService) throws Exception {
        Oks ok = new Oks();
        this.aclService = aclService;
        init(ok);
        if (ok.is == false) {
            throw new Exception(ok.getTxt());
        }
    }

    public boolean init(Oks ok, Object ... extras_array) throws Exception {
        if (ok.is == false) { return false; }
        try {
            Acl_actorsEntity acl_actorsEntity = new Acl_actorsEntity();
            acl_actorsEntity.setId_acl_actor(k_acl_jpaRepository_admin);
            aclService.setAcl_actorsEntity_to_check(acl_actorsEntity);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.is;
    }
    // Just for testing
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    @GetMapping("/test/1")
    public ResponseEntity<? extends Object> test_save_admin() throws Exception {
        ResponseEntity<Acl_actorsEntity> retorno = null;
        Oks ok = new Oks();
        do {
            try {
                aclService.getAcl_actorsEntity_to_check().setId_acl_actor(k_acl_jpaRepository_admin);
                Acl_actorsEntity acl_actorsEntity = new Acl_actorsEntity();
                acl_actorsEntity.setId_acl_actor("jpaRepository_write");
                acl_actorsEntity.setPrincipal_type(0);
                acl_actorsEntity = aclService.aclRepository.getAcl_actorsRepository().save(acl_actorsEntity);
                retorno = new ResponseEntity<> (acl_actorsEntity, HttpStatus.OK);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            return new ResponseEntity<String>(ok.getTxt(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return retorno;
    }
    // Just for testing
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    @GetMapping("/test/2")
    public ResponseEntity<? extends Object> test_save_group() throws Exception {
        ResponseEntity<Acl_actorsEntity> retorno = null;
        Oks ok = new Oks();
        do {
            try {
                aclService.getAcl_actorsEntity_to_check().setId_acl_actor(k_acl_jpaRepository_group);
                Acl_actorsEntity acl_actorsEntity = new Acl_actorsEntity();
                acl_actorsEntity.setId_acl_actor("jpaRepository_write");
                acl_actorsEntity.setPrincipal_type(0);
                acl_actorsEntity = aclService.aclRepository.getAcl_actorsRepository().save(acl_actorsEntity);
                retorno = new ResponseEntity<> (acl_actorsEntity, HttpStatus.OK);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            return new ResponseEntity<String>(ok.getTxt(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return retorno;
    }

}
