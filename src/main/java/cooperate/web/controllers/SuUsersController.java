package cooperate.web.controllers;

import cooperate.app.business.role.RoleService;
import cooperate.app.business.user.UserService;
import cooperate.app.business.user.activateUser.ActivateUserCommand;
import cooperate.app.business.user.adduser.AddUserCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.UserDto;
import cooperate.infrastructure.dto.UserFilterDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.UserEditModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class SuUsersController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

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

    @HasPermission(to = {PermissionConstants.UserCreate})
    @RequestMapping(value = "/su/users/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/users/create");
        mav.addObject("user", new UserEditModel());
        mav.addObject("roles", roleService.getRolesList());
        return mav;
    }

    @HasPermission(to = {PermissionConstants.UserCreate})
    @RequestMapping(value = "/su/users/creteUser", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createUser(@Valid @ModelAttribute("user") UserEditModel userEditModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("/superuser/users/create");
        mav.addObject("roles", roleService.getRolesList());
        if (result.hasErrors()) {
            mav.addObject("user", userEditModel);
            return mav;
        }
        AddUserCommand command = (AddUserCommand) context.getBean("addUserCommand");
        command.setFullName(userEditModel.getFullName());
        command.setPassword(userEditModel.getPassword());
        command.setEmail(userEditModel.getEmail());
        command.setRoleId(userEditModel.getRoleId());
        userService.AddUser(command);
        mav.addObject("message", context.getMessage("message.userAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


    @HasPermission(to = {PermissionConstants.UserIndex})
    @RequestMapping(value = "/su/users/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int userId) throws Exception {

        ActivateUserCommand command = (ActivateUserCommand) context.getBean("activateUserCommand");
        command.setUserId(userId);
        command.setUpdateUserId(getSessionUser().UserId);
        userService.ActivateUser(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }



}