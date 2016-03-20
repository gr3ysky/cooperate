package cooperate.web.controllers;

import cooperate.app.business.user.UserService;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.UserDto;
import cooperate.infrastructure.dto.UserFilterDto;
import cooperate.web.core.HasPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;
@Controller
public class SuUsersController extends BaseController {

    @Autowired
    UserService userService;

    @HasPermission(to = {PermissionConstants.UserIndex})
    @RequestMapping("/su/users")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/users/index");
        mav.addObject("pageDescription", context.getMessage("page.description.superuser.index", null, Locale.getDefault()));
        return mav;
    }
    @HasPermission(to = {PermissionConstants.UserIndex})
    @RequestMapping(value = "/su/users/test")
    public ResponseEntity<ListResponse<UserDto>> test(@Valid @ModelAttribute("request") ListRequest<UserFilterDto, UserDto> request, @ModelAttribute("filter") UserFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<UserDto> response = userService.ListUsers(request);
        return new ResponseEntity<ListResponse<UserDto>>(response, httpHeaders, HttpStatus.OK);

    }
}