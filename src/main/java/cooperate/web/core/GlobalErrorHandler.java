package cooperate.web.core;

import cooperate.infrastructure.exception.CoopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@EnableWebMvc
@ControllerAdvice
public class GlobalErrorHandler implements HandlerExceptionResolver {

    private static final String GlobalErrorViewName = "shared/error";
    @Autowired
    ApplicationContext context;

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        switch (getReponseTypeFromRequest(request)) {
            case Html:
                return handleHtmlError(ex);
            default:
                return handleJsonError(ex);
        }
    }

    @ResponseBody
    private ModelAndView handleJsonError(Exception e) {
        ModelAndView model = new ModelAndView();
        model.addObject("status", "error");
        model.addObject("message", e.getMessage());
        return model;
    }

    private ModelAndView handleHtmlError(Exception e) {
        ModelAndView model = new ModelAndView(GlobalErrorViewName);
        if (e instanceof ClassCastException) {
            model.setViewName("redirect:/404");
            return model;
        }
        if (e instanceof CoopException) {
            model.addObject("message", String.format(context.getMessage(e.getMessage(), null, Locale.getDefault()), ((CoopException) e).getParams()));
        } else
            model.addObject("message", e.getMessage());

        if (e.getMessage() == "UnauthorizedException")
            model.setViewName("redirect:/login");
        return model;
    }

    private ResponseType getReponseTypeFromRequest(HttpServletRequest request) {
        if (request.getHeader("accept").contains("text/html"))
            return ResponseType.Html;
        return ResponseType.Json;
    }

    protected enum ResponseType {
        Html,
        Json
    }
}
