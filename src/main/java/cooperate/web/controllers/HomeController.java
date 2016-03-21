package cooperate.web.controllers;

import cooperate.app.business.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {
    @Autowired
    private UserService _service;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index(HttpSession session) throws Exception {
        ModelAndView model = new ModelAndView("home/index");
        model.addObject("a", RequestContextHolder.currentRequestAttributes().getSessionId());
        model.addObject("User", session.getAttribute("taner"));


        return model;
    }
}
