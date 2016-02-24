package cooperate.web.controllers;

import cooperate.app.business.user.UserService;
import cooperate.app.business.user.login.LoginRequest;
import cooperate.app.business.user.login.LoginResponse;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.infrastructure.security.DesEncrypter;
import cooperate.web.viewmodels.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService loginService;

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
    public ModelAndView dologin(Model model, @Valid @ModelAttribute("login") LoginModel loginModel, BindingResult result, HttpSession session, HttpServletResponse response) throws Exception {
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
                session.setAttribute(SessionConstants.User, login.getLoginDto());
                if (loginModel.getRememberMe()) {

                    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
                    DesEncrypter encrypter = new DesEncrypter(key);
                    String encrypted = encrypter.encrypt(loginModel.getEmail());
                    Cookie rememberMeCookie = new Cookie(SessionConstants.RememberMeCookieName, encrypted);
                    rememberMeCookie.setSecure(true);
                    rememberMeCookie.setMaxAge(100000);
                    response.addCookie(rememberMeCookie);
                }

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
