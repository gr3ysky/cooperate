package cooperate.web.controllers;

import cooperate.app.business.unit.UnitService;
import cooperate.app.business.unit.activateUnit.ActivateUnitCommand;
import cooperate.app.business.unit.addUnit.AddUnitCommand;
import cooperate.app.business.unit.getUnit.GetUnitRequest;
import cooperate.app.business.unit.updateUnit.UpdateUnitCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.unit.UnitDto;
import cooperate.infrastructure.dto.unit.UnitFilterDto;
import cooperate.infrastructure.dto.unit.UnitListDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.unit.UnitCreateViewModel;
import cooperate.web.viewmodels.unit.UnitUpdateViewModel;
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
public class SuUnitController extends BaseController {

    @Autowired
    UnitService unitService;

    @HasPermission(to = {PermissionConstants.UnitIndex})
    @RequestMapping("/su/unit")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/unit/index");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.UnitIndex})
    @RequestMapping(value = "/su/unit/list")
    public ResponseEntity<ListResponse<UnitListDto>> list(@Valid @ModelAttribute("request") ListRequest<UnitFilterDto, UnitListDto> request, @ModelAttribute("filter") UnitFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<UnitListDto> response = unitService.ListUnits(request);
        return new ResponseEntity<ListResponse<UnitListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.UnitCreate})
    @RequestMapping(value = "/su/unit/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/unit/create");
        mav.addObject("unit", new UnitCreateViewModel());
        return mav;

    }

    @HasPermission(to = {PermissionConstants.UnitCreate})
    @RequestMapping(value = "/su/unit/create", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createUnit(@Valid @ModelAttribute("unit") UnitCreateViewModel unitCreateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/unit/create");
        if (result.hasErrors()) {
            mav.addObject("unit", unitCreateViewModel);
            return mav;
        }
        AddUnitCommand command = (AddUnitCommand) context.getBean("addUnitCommand");
        command.setResourceKey(unitCreateViewModel.getResourceKey());
        command.setName(unitCreateViewModel.getName());
        command.setActive(unitCreateViewModel.getIsActive());
        command.setCreateUserId(getSessionUser().UserId);
        unitService.AddUnit(command);
        mav.addObject("unit", new UnitCreateViewModel());
        mav.addObject("message", context.getMessage("message.unitAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.UnitUpdate})
    @RequestMapping(value = "/su/unit/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int unitId) throws Exception {

        ActivateUnitCommand command = (ActivateUnitCommand) context.getBean("activateUnitCommand");
        command.setUpdateUserId(getSessionUser().UserId);
        command.setUnitId(unitId);
        unitService.ActivateUnit(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.UnitUpdate})
    @RequestMapping(value = "/su/unit/update/{unitId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int unitId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/unit/update");
        GetUnitRequest request = (GetUnitRequest) context.getBean("getUnitRequest");
        request.setUnitId(unitId);
        UnitDto dto = unitService.GetUnitDto(request);
        UnitUpdateViewModel model = new UnitUpdateViewModel();
        model.setIsActive(dto.getIsActive());
        model.setResourceKey(dto.getResourceKey());
        model.setName(dto.getName());
        model.setUnitId(dto.getUnitId());
        mav.addObject("unit", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.UnitUpdate})
    @RequestMapping(value = "/su/unit/update", method = RequestMethod.POST)
    public ModelAndView updateUnit(@Valid @ModelAttribute("unit") UnitUpdateViewModel unitUpdateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/unit/update");
        if (result.hasErrors()) {
            mav.addObject("unit", unitUpdateViewModel);
            return mav;
        }
        UpdateUnitCommand command = (UpdateUnitCommand) context.getBean("updateUnitCommand");
        command.setResourceKey(unitUpdateViewModel.getResourceKey());
        command.setName(unitUpdateViewModel.getName());
        command.setIsActive(unitUpdateViewModel.getIsActive());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setUnitId(unitUpdateViewModel.getUnitId());
        unitService.UpdateUnit(command);
        mav.addObject("unit", unitUpdateViewModel);
        mav.addObject("message", context.getMessage("message.unitUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }
}
