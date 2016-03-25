package cooperate.web.core;

import cooperate.infrastructure.exception.CoopException;
import cooperate.web.viewmodels.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    private static final String GlobalErrorViewName = "shared/error";
    @Autowired
    ApplicationContext context;


    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected Object handleInvalidRequest(Exception e, WebRequest request) {
        if (isAjax(request)) {
            AjaxResponse response = new AjaxResponse();
            response.setStatus("error");
            if (e instanceof CoopException) {
                response.setMessage(String.format(context.getMessage(e.getMessage(), null, Locale.getDefault()), ((CoopException) e).getParams()));
            } else
                response.setMessage(e.getMessage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<Object>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ModelAndView model = new ModelAndView(GlobalErrorViewName);
            if (e instanceof ClassCastException) {
                model.setViewName("redirect:/404");
            }
            if (e instanceof CoopException) {
                model.addObject("message", String.format(context.getMessage(e.getMessage(), null, Locale.getDefault()), ((CoopException) e).getParams()));
            } else
                model.addObject("message", e.getMessage());

            if (e.getMessage() == "UnauthorizedException")
                model.setViewName("redirect:/login");
            model.addObject("status", "error");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            return model;
        }
    }

    private boolean isAjax(WebRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWithHeader);
    }


}
