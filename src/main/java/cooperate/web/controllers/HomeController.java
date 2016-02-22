package cooperate.web.controllers;

import cooperate.app.business.UserService;
import cooperate.app.business.adduser.AddUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    @Autowired
    private UserService _service;


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index() throws Exception {
        ModelAndView model = new ModelAndView("home/index");
        model.addObject("a", "yiğit");
        AddUserCommand command = (AddUserCommand) context.getBean("addUserCommand");
        command.setPassword("123456");
        command.setEmail("taneryigit@live.com");
        command.setFullName("Taner Yiğit");
        _service.AddUser(command);
        return model;
    }
}
