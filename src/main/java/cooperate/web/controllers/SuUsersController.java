package cooperate.web.controllers;

import cooperate.app.business.role.RoleService;
import cooperate.app.business.user.UserService;
import cooperate.app.business.user.activateUser.ActivateUserCommand;
import cooperate.app.business.user.adduser.AddUserCommand;
import cooperate.app.business.user.getUser.GetUserRequest;
import cooperate.app.business.user.updateUser.UpdateUserCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.user.UserDto;
import cooperate.infrastructure.dto.user.UserFilterDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.UserCreateModel;
import cooperate.web.viewmodels.UserUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/su/users/list")
    public ResponseEntity<ListResponse<UserDto>> list(@Valid @ModelAttribute("request") ListRequest<UserFilterDto, UserDto> request, @ModelAttribute("filter") UserFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        filter.setRoleId(-1);
        request.setFilter(filter);
        ListResponse<UserDto> response = userService.ListUsers(request);
        return new ResponseEntity<ListResponse<UserDto>>(response, httpHeaders, HttpStatus.OK);

    }

    @HasPermission(to = {PermissionConstants.UserCreate})
    @RequestMapping(value = "/su/users/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/users/create");
        mav.addObject("user", new UserCreateModel());
        mav.addObject("roles", roleService.getRolesList());
        return mav;
    }

    @HasPermission(to = {PermissionConstants.UserUpdate})
    @RequestMapping(value = "/su/users/update/{userId}")
    public ModelAndView update(@PathVariable int userId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/users/update");
        GetUserRequest request = (GetUserRequest) context.getBean("getUserRequest");
        request.setUserId(userId);
        UserDto dto = userService.GetUserDto(request);

        UserUpdateModel model = new UserUpdateModel();
        model.setRoleId(dto.getRoleId());
        model.setFullName(dto.getFullName());
        model.setUserId(dto.getUserId());
        model.setEmail(dto.getEmail());
        model.setIsActive(dto.getIsActive());
        mav.addObject("user", model);
        mav.addObject("roles", roleService.getRolesList());
        return mav;
    }


    @HasPermission(to = {PermissionConstants.UserCreate})
    @RequestMapping(value = "/su/users/creteUser", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createUser(@Valid @ModelAttribute("user") UserCreateModel userCreateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/users/create");
        mav.addObject("roles", roleService.getRolesList());
        if (result.hasErrors()) {
            mav.addObject("user", userCreateModel);
            mav.setViewName("superuser/users/create");
            return mav;
        }
        AddUserCommand command = (AddUserCommand) context.getBean("addUserCommand");
        command.setFullName(userCreateModel.getFullName());
        command.setPassword(userCreateModel.getPassword());
        command.setEmail(userCreateModel.getEmail());
        command.setRoleId(userCreateModel.getRoleId());
        userService.AddUser(command);
        mav.addObject("user", new UserCreateModel());
        mav.addObject("message", context.getMessage("message.userAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


    @HasPermission(to = {PermissionConstants.UserUpdate})
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

    @HasPermission(to = {PermissionConstants.UserUpdate})
    @RequestMapping(value = "/su/users/updateUser", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView updateUser(@Valid @ModelAttribute("user") UserUpdateModel userUpdateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/users/update");
        mav.addObject("roles", roleService.getRolesList());
        if (result.hasErrors()) {
            mav.addObject("user", userUpdateModel);
            return mav;
        }
        UpdateUserCommand command = (UpdateUserCommand) context.getBean("updateUserCommand");
        command.setFullName(userUpdateModel.getFullName());
        command.setEmail(userUpdateModel.getEmail());
        command.setRoleId(userUpdateModel.getRoleId());
        command.setIsActive(userUpdateModel.getIsActive());
        command.setUserId(userUpdateModel.getUserId());
        command.setUpdateUserId(getSessionUser().UserId);
        userService.UpdateUser(command);
        mav.addObject("user", userUpdateModel);
        mav.addObject("message", context.getMessage("message.userUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

}