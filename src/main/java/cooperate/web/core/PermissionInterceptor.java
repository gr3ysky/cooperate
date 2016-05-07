package cooperate.web.core;

import cooperate.app.business.permission.PermissionService;
import cooperate.app.business.user.UserService;
import cooperate.app.business.user.login.LoginDto;
import cooperate.app.business.user.login.LoginDtoRequest;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.infrastructure.dto.RolePermissionDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.security.DesEncrypter;
import cooperate.infrastructure.security.PermissionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getServletPath().equals("/login"))
            return super.preHandle(request, response, handler);
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
                    String email = DesEncrypter.decrypt(authCookie.getValue());
                    LoginDtoRequest loginDtoRequest = (LoginDtoRequest) context.getBean("loginDtoRequest");
                    loginDtoRequest.setEmail(email);
                    loginDto = userService.getUserByEmail(loginDtoRequest);
                    if (loginDto == null) throw new Exception("UnauthorizedException");
                    request.getSession().setAttribute(SessionConstants.User, loginDto);
                }
            }
            if (loginDto != null) {
                String[] permissions = hasPermission.to();
                List<RolePermissionDto> rolePermissionDtos = permissionService.getRolePermissionList();
                for (String perm : permissions) {
                    if (!request.getServletPath().equals("/partial/menu") && !PermissionManager.CheckPermission(loginDto.RoleId, perm, rolePermissionDtos)) {
                        throw new CoopException("error.notAuthorized");
                    }
                }


            } else if (request.getServletPath().equals("/partial/menu")) {

                //throw new Exception("Your session has expired");
            }
        }
        return super.preHandle(request, response, handler);
    }
}
