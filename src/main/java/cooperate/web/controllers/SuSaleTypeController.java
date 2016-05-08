package cooperate.web.controllers;

import cooperate.app.business.saleType.SaleTypeService;
import cooperate.app.business.saleType.activateSaleType.ActivateSaleTypeCommand;
import cooperate.app.business.saleType.addSaleType.AddSaleTypeCommand;
import cooperate.app.business.saleType.getSaleType.GetSaleTypeRequest;
import cooperate.app.business.saleType.updateSaleType.UpdateSaleTypeCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.saleType.SaleTypeDto;
import cooperate.infrastructure.dto.saleType.SaleTypeFilterDto;
import cooperate.infrastructure.dto.saleType.SaleTypeListDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.saleType.SaleTypeCreateViewModel;
import cooperate.web.viewmodels.saleType.SaleTypeUpdateViewModel;
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
public class SuSaleTypeController extends BaseController {
    @Autowired
    SaleTypeService saleTypeService;

    @HasPermission(to = {PermissionConstants.SaleTypeIndex})
    @RequestMapping("/su/sale-type")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/saleType/index");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.SaleTypeIndex})
    @RequestMapping(value = "/su/sale-type/list")
    public ResponseEntity<ListResponse<SaleTypeListDto>> list(@Valid @ModelAttribute("request") ListRequest<SaleTypeFilterDto, SaleTypeListDto> request, @ModelAttribute("filter") SaleTypeFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<SaleTypeListDto> response = saleTypeService.ListSaleTypes(request);
        return new ResponseEntity<ListResponse<SaleTypeListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.SaleTypeCreate})
    @RequestMapping(value = "/su/sale-type/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/saleType/create");
        mav.addObject("saleType", new SaleTypeCreateViewModel());
        return mav;

    }

    @HasPermission(to = {PermissionConstants.SaleTypeCreate})
    @RequestMapping(value = "/su/sale-type/create", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createSaleType(@Valid @ModelAttribute("saleType") SaleTypeCreateViewModel saleTypeCreateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/saleType/create");
        if (result.hasErrors()) {
            mav.addObject("sale", saleTypeCreateViewModel);
            return mav;
        }
        AddSaleTypeCommand command = (AddSaleTypeCommand) context.getBean("addSaleTypeCommand");
        command.setResourceKey(saleTypeCreateViewModel.getResourceKey());
        command.setName(saleTypeCreateViewModel.getName());
        command.setActive(saleTypeCreateViewModel.getIsActive());
        command.setCreateUserId(getSessionUser().UserId);
        saleTypeService.AddSaleType(command);
        mav.addObject("saleType", new SaleTypeCreateViewModel());
        mav.addObject("message", context.getMessage("message.saleTypeAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.SaleTypeUpdate})
    @RequestMapping(value = "/su/sale-type/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int saleTypeId) throws Exception {

        ActivateSaleTypeCommand command = (ActivateSaleTypeCommand) context.getBean("activateSaleTypeCommand");
        command.setUpdateUserId(getSessionUser().UserId);
        command.setSaleTypeId(saleTypeId);
        saleTypeService.ActivateSaleType(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.SaleTypeUpdate})
    @RequestMapping(value = "/su/sale-type/update/{saleTypeId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int saleTypeId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/saleType/update");
        GetSaleTypeRequest request = (GetSaleTypeRequest) context.getBean("getSaleTypeRequest");
        request.setSaleTypeId(saleTypeId);
        SaleTypeDto dto = saleTypeService.GetSaleTypeDto(request);
        SaleTypeUpdateViewModel model = new SaleTypeUpdateViewModel();
        model.setIsActive(dto.getIsActive());
        model.setResourceKey(dto.getResourceKey());
        model.setName(dto.getName());
        model.setSaleTypeId(dto.getSaleTypeId());
        mav.addObject("saleType", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.SaleTypeUpdate})
    @RequestMapping(value = "/su/sale-type/update", method = RequestMethod.POST)
    public ModelAndView updateSaleType(@Valid @ModelAttribute("saleType") SaleTypeUpdateViewModel saleTypeUpdateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/saleType/update");
        if (result.hasErrors()) {
            mav.addObject("saleType", saleTypeUpdateViewModel);
            return mav;
        }
        UpdateSaleTypeCommand command = (UpdateSaleTypeCommand) context.getBean("updateSaleTypeCommand");
        command.setResourceKey(saleTypeUpdateViewModel.getResourceKey());
        command.setName(saleTypeUpdateViewModel.getName());
        command.setIsActive(saleTypeUpdateViewModel.getIsActive());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setSaleTypeId(saleTypeUpdateViewModel.getSaleTypeId());
        saleTypeService.UpdateSaleType(command);
        mav.addObject("saleType", saleTypeUpdateViewModel);
        mav.addObject("message", context.getMessage("message.saleTypeUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

}
