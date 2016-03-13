package cooperate.web.controllers;

import cooperate.app.business.user.login.LoginDto;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListResponse;
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

    @RequestMapping(value = "/su/test")
    public ResponseEntity<ListResponse<LoginDto>> test() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ListResponse<LoginDto> response = new ListResponse<LoginDto>();
        response.setDraw(1);
        response.setFilteredCount(1);
        response.setTotal(1);
        List<LoginDto> data = new ArrayList<LoginDto>();
        LoginDto dto = new LoginDto();
        dto.Fullname = "taner";
        dto.RoleName = "admin";
        data.add(dto);
        response.setData(data);
        return new ResponseEntity<ListResponse<LoginDto>>(response, httpHeaders, HttpStatus.OK);

    }

}
