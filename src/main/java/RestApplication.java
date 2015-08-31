import com.homemonitoring.rest.AlertService;
import com.homemonitoring.rest.CloseConnectionService;
import com.homemonitoring.rest.GraphService;
import com.homemonitoring.rest.SaveService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApplication extends Application {

    final Set<Class<?>> classes = new HashSet<Class<?>>();

    @Override
    public Set<Class<?>> getClasses() {
        classes.add(AlertService.class);
        classes.add(SaveService.class);
        classes.add(GraphService.class);
        classes.add(CloseConnectionService.class);
        return Collections.unmodifiableSet(classes);
    }

}