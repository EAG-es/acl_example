package inser.spring.restful.acl_example;

import innui.modelos.Modelos;
import innui.modelos.configurations.Initials;
import innui.modelos.errors.Oks;
import static java.lang.System.exit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Acl_exampleApplication extends Initials implements AutoCloseable {

    public Acl_exampleApplication() throws Exception {
        Oks ok = new Oks();
        init(ok);
        if (ok.is == false) {
            throw new Exception(ok.getTxt());
        }
    }

    @Override
    protected void finalize() throws Exception, Throwable {
        close();
        super.finalize();
    }

    public static void main(String[] args) {
        Oks ok = new Oks();
        try {
            SpringApplication.run(Acl_exampleApplication.class, args);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.is == false) {
            System.err.println(ok.txt);
            exit(1);
        }
    }

    @Override
    public boolean run(Oks ok, Object ... extras_array) throws Exception {
        return ok.is;
    }

    @Override
    public boolean init(Oks ok, Object... extra_array) throws Exception {
        // Init models for the clases
        // Models: persistent properties (re), internationalization (in),...
        if (ok.is == false) { return ok.is; }
        _init_from_class(Modelos.class, ok);
        if (ok.is == false) { return ok.is; }
        _init_from_class(this.getClass(), ok);
        return ok.is;
    }

    @Override
    public boolean terminate(Oks ok, Object... extra_array) throws Exception {
        // Terminate models for the clases
        // Models: persistent properties (re), internationalization (in),...
        if (ok.is == false) { return ok.is; }
        _terminate_from_class(Modelos.class, ok);
        if (ok.is == false) { return ok.is; }
        _terminate_from_class(this.getClass(), ok);
        if (ok.is == false) { return ok.is; }
        return ok.is;
    }

    @Override
    public void close() throws Exception {
        Oks ok = new Oks();
        terminate(ok);
        if (ok.is == false) {
            throw new Exception(ok.getTxt());
        }
    }

}
