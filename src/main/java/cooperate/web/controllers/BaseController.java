package cooperate.web.controllers;

import cooperate.app.business.user.login.LoginDto;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.GenericAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class BaseController {
    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected HttpSession session;

    protected AjaxResponse getAjaxResponse(String messagekey, String status) {
        AjaxResponse response = new AjaxResponse();
        response.setStatus(status);
        response.setMessage(context.getMessage(messagekey, null, Locale.getDefault()));
        return response;
    }

    protected <T> GenericAjaxResponse<T> getGenericAjaxResponse(String messagekey, String status, T data) {
        GenericAjaxResponse<T> response = new GenericAjaxResponse<T>();
        response.setStatus(status);
        response.setMessage(context.getMessage(messagekey, null, Locale.getDefault()));
        response.setData(data);
        return response;
    }

    protected LoginDto getSessionUser() {
        return (LoginDto) session.getAttribute(SessionConstants.User);
    }

}
