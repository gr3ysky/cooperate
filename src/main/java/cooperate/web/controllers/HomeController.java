package cooperate.web.controllers;

import cooperate.app.business.user.UserService;
import cooperate.web.core.HasPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
public class HomeController extends BaseController {
    @Autowired
    private UserService _service;

    @HasPermission(to = {"PERMISSION_HOME_INDEX"})
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index(HttpSession session) throws Exception {
        ModelAndView model = new ModelAndView("home/index");
        model.addObject("a", "yiğit");
       /* AddUserCommand command=(AddUserCommand)context.getBean("addUserCommand");
        command.setFullName("Taner Yiğit");
        command.setPassword("123456");
        command.setEmail("taneryigit@live.com");
        command.setRoleId((short) 1);
        _service.AddUser(command);*/
        return model;
    }
}
