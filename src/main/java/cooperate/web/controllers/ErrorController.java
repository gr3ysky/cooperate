package cooperate.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @RequestMapping("/404")
    public String notFound() {
        return "shared/404";
    }
}
