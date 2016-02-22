package cooperate.web.controllers;

import cooperate.app.business.login.LoginRequest;
import cooperate.app.business.login.LoginResponse;
import cooperate.app.business.login.LoginService;
import cooperate.web.viewmodels.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login/index");
        LoginModel loginModel = new LoginModel();
        loginModel.setRememberMe(true);
        model.addObject("login", loginModel);
        model.addObject("pageDescription", context.getMessage("page.description.login.index", null, Locale.getDefault()));
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView dologin(Model model, @Valid @ModelAttribute("login") LoginModel loginModel, BindingResult result) throws Exception {
        ModelAndView modelAndView = new ModelAndView("login/index");

        if (result.hasErrors()) {

            modelAndView.addObject("message", context.getMessage("error.loginfailed", null, Locale.getDefault()));
            modelAndView.addObject("pageDescription", context.getMessage("page.description.login.index", null, Locale.getDefault()));
            modelAndView.addObject("login", loginModel);
        } else {

            LoginRequest loginRequest = (LoginRequest) context.getBean("loginRequest");
            loginRequest.setEmail(loginModel.getEmail());
            loginRequest.setPassword(loginModel.getPassword());

            LoginResponse login = loginService.Login(loginRequest);
            if (login.isSuccess()) {
                modelAndView.setViewName("redirect:/");
            } else {
                modelAndView.addObject("message", context.getMessage("error.loginfailed", null, Locale.getDefault()));
                modelAndView.addObject("pageDescription", context.getMessage("page.description.login.index", null, Locale.getDefault()));
                modelAndView.addObject("login", loginModel);
            }
        }
        return modelAndView;
    }
}
