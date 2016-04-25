package cooperate.web.controllers;

import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.user.UserDto;
import cooperate.web.core.HasPermission;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class SuperUserController extends BaseController {
    @HasPermission(to = {PermissionConstants.SystemManagement})
    @RequestMapping("/su/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/index");
        mav.addObject("pageDescription", context.getMessage("page.description.superuser.index", null, Locale.getDefault()));
        return mav;
    }
    @HasPermission(to = {PermissionConstants.SystemManagement})
    @RequestMapping(value = "/su/test")
    public ResponseEntity<ListResponse<UserDto>> test() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ListResponse<UserDto> response = new ListResponse<UserDto>();
        response.setDraw(1);
        response.setRecordsFiltered(1);
        response.setRecordsTotal(1);
        List<UserDto> data = new ArrayList<UserDto>();
        UserDto dto = new UserDto();
        dto.setUserId(1);
        dto.setFullName("taner");
        dto.setEmail("taneryigit@boun.edu.tr");
        dto.setIsActive(true);
        data.add(dto);
        response.setData(data);
        return new ResponseEntity<ListResponse<UserDto>>(response, httpHeaders, HttpStatus.OK);

    }

}
