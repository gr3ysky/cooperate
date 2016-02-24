package cooperate.web.core;

import cooperate.app.business.user.UserService;
import cooperate.app.business.user.login.LoginDto;
import cooperate.app.business.user.login.LoginDtoRequest;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.infrastructure.security.DesEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by taner on 23.02.2016.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ApplicationContext context;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        HasPermission hasPermission = handlerMethod.getMethodAnnotation(HasPermission.class);
        if (hasPermission != null) {
            LoginDto loginDto = (LoginDto) request.getSession().getAttribute(SessionConstants.User);
            if (loginDto == null) {
                Cookie authCookie = null;
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(SessionConstants.RememberMeCookieName)) {
                        authCookie = cookie;
                        break;
                    }
                }
                if (authCookie != null) {
                    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
                    DesEncrypter encrypter = new DesEncrypter(key);
                    String email = encrypter.decrypt(authCookie.getValue());
                    LoginDtoRequest loginDtoRequest = (LoginDtoRequest) context.getBean("loginRequest");
                    loginDtoRequest.setEmail(email);
                    loginDto = userService.getUserByEmail(loginDtoRequest);
                    request.getSession().setAttribute(SessionConstants.User, loginDto);
                }
            }
            if (loginDto != null) {
                String[] permissions = hasPermission.to();
                //TODO: implement permission check
            } else {
                throw new Exception("Your session has expired");
            }
        }
        return super.preHandle(request, response, handler);
    }
}
