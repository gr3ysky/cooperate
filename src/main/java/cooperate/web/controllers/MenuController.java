package cooperate.web.controllers;

import cooperate.app.business.menu.MenuService;
import cooperate.app.business.permission.PermissionService;
import cooperate.app.business.user.login.LoginDto;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.infrastructure.dto.MenuDto;
import cooperate.infrastructure.dto.RolePermissionDto;
import cooperate.infrastructure.security.PermissionManager;
import cooperate.web.core.HasPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @Autowired
    PermissionService permissionService;

    @HasPermission(to = {PermissionConstants.MenuIndex})
    @RequestMapping(value = "/partial/menu", method = RequestMethod.GET)
    public ModelAndView menu(HttpSession sesion) throws Exception {
        LoginDto loginDto = (LoginDto) sesion.getAttribute(SessionConstants.User);
        List<RolePermissionDto> rolePermissionDtos = permissionService.getRolePermissionList();
        ModelAndView mav = new ModelAndView("shared/menu");
        List<MenuDto> menu = menuService.getMenu();
        mav.addObject("Menu", getParentMenu(menu, loginDto, rolePermissionDtos));
        return mav;
    }

    private List<MenuDto> getParentMenu(List<MenuDto> menu, LoginDto loginDto, List<RolePermissionDto> rolePermissionDtos) {
        List<MenuDto> parentMenu = new ArrayList<MenuDto>() {
        };
        for (MenuDto dto : menu) {
            if (dto.getLanguageCode().equals(Locale.getDefault().getCountry())) {
                if (loginDto != null && dto.getPermissionCode() != null) {
                    if (dto.getParentMenuId() == 0 && PermissionManager.CheckPermission(loginDto.RoleId, dto.getPermissionCode(), rolePermissionDtos)) {
                        parentMenu.add(dto);
                        //menu.remove(parentMenu);
                    }
                } else if (dto.getParentMenuId() == 0 && dto.getPermissionCode() == null) {
                    parentMenu.add(dto);
                }
            }
        }
        for (MenuDto parent : parentMenu) {
            for (MenuDto dto : menu) {
                if (dto.getLanguageCode().equals(Locale.getDefault().getCountry())) {
                    parent.setChildren(new ArrayList<MenuDto>());
                    if (loginDto != null && dto.getPermissionCode() != null) {
                        if (dto.getParentMenuId() == parent.getMenuId() && PermissionManager.CheckPermission(loginDto.RoleId, dto.getPermissionCode(), rolePermissionDtos)) {
                            parent.getChildren().add(dto);
                        }
                    } else if (dto.getParentMenuId() == parent.getMenuId() && dto.getPermissionCode() == null) {
                        parent.getChildren().add(dto);
                    }
                }
            }
        }
        return parentMenu;
    }

}
