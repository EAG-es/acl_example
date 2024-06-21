package inser.spring.restful.acl_example.controller;

import innui.modelos.errors.Oks;
import inser.spring.restful.acl_example.entity.Acl_actorsEntity;
import static inser.spring.restful.acl_example.repository.Acl_jpaRepository.k_acl_jpaRepository_group;
import inser.spring.restful.acl_example.service.AclService;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            acl_actorsEntity.setId_acl_actor(k_acl_jpaRepository_group);
            aclService.setAcl_actorsEntity(acl_actorsEntity);
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
    @GetMapping("/test")
    public ResponseEntity<String> test() throws Exception {
        ResponseEntity<String> retorno = null;
        Oks ok = new Oks();
        do {
            try {
                long count;
                count = aclService.aclRepository.getAcl_actorsRepository().count();
                retorno = new ResponseEntity<> (String.valueOf(count), HttpStatus.OK);

            } catch (Exception e) {
                ok.setTxt(e);
            }
        } while (false);
        if (ok.is == false) {
            retorno = new ResponseEntity<>(ok.getTxt(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return retorno;
    }

}
