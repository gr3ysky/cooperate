package cooperate.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    protected ApplicationContext context;
}
