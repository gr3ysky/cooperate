package cooperate.web.controllers;

import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.web.core.HasPermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class SuperUserController extends BaseController {
    @HasPermission(to = {PermissionConstants.SystemManagement})
    @RequestMapping("/super-user")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/index");
        mav.addObject("pageDescription", context.getMessage("page.description.superuser.index", null, Locale.getDefault()));
        return mav;
    }
}
