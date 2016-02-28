package cooperate.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class ServiceBase {
    @Autowired
    protected ApplicationContext context;
}
