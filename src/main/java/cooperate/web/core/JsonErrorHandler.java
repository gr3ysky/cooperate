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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class JsonErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ApplicationContext context;

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<AjaxResponse> handleInvalidRequest(Exception e, WebRequest request) {
        AjaxResponse response = new AjaxResponse();
        response.setStatus("error");
        if (e instanceof CoopException) {
            response.setMessage(String.format(context.getMessage(e.getMessage(), null, Locale.getDefault()), ((CoopException) e).getParams()));
        } else
            response.setMessage(e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
