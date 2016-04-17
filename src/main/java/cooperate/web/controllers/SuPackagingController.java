package cooperate.web.controllers;

import cooperate.app.business.packaging.PackagingService;
import cooperate.app.business.packaging.activatePackaging.ActivatePackagingCommand;
import cooperate.app.business.packaging.addPackaging.AddPackagingCommand;
import cooperate.app.business.packaging.getPackaging.GetPackagingRequest;
import cooperate.app.business.packaging.updatePackaging.UpdatePackagingCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.packaging.PackagingDto;
import cooperate.infrastructure.dto.packaging.PackagingFilterDto;
import cooperate.infrastructure.dto.packaging.PackagingListDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.packaging.PackagingCreateViewModel;
import cooperate.web.viewmodels.packaging.PackagingUpdateViewModel;
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
public class SuPackagingController extends BaseController {

    @Autowired
    PackagingService packagingService;

    @HasPermission(to = {PermissionConstants.PackagingIndex})
    @RequestMapping("/su/packaging")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/packaging/index");
        mav.addObject("pageDescription", context.getMessage("page.description.packaging.index", null, Locale.getDefault()));
        return mav;
    }

    @HasPermission(to = {PermissionConstants.PackagingIndex})
    @RequestMapping(value = "/su/packaging/list")
    public ResponseEntity<ListResponse<PackagingListDto>> list(@Valid @ModelAttribute("request") ListRequest<PackagingFilterDto, PackagingListDto> request, @ModelAttribute("filter") PackagingFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<PackagingListDto> response = packagingService.ListPackagings(request);
        return new ResponseEntity<ListResponse<PackagingListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.PackagingCreate})
    @RequestMapping(value = "/su/packaging/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/packaging/create");
        mav.addObject("packaging", new PackagingCreateViewModel());
        return mav;

    }

    @HasPermission(to = {PermissionConstants.PackagingCreate})
    @RequestMapping(value = "/su/packaging/create", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProductCategory(@Valid @ModelAttribute("packaging") PackagingCreateViewModel packagingCreateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/packaging/create");
        if (result.hasErrors()) {
            mav.addObject("packaging", packagingCreateViewModel);
            return mav;
        }
        AddPackagingCommand command = (AddPackagingCommand) context.getBean("addPackagingCommand");
        command.setResourceKey(packagingCreateViewModel.getResourceKey());
        command.setName(packagingCreateViewModel.getName());
        command.setActive(packagingCreateViewModel.getIsActive());
        command.setCreateUserId(getSessionUser().UserId);
        packagingService.AddPackaging(command);
        mav.addObject("packaging", new PackagingCreateViewModel());
        mav.addObject("message", context.getMessage("message.packagingAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.PackagingUpdate})
    @RequestMapping(value = "/su/packaging/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int packagingId) throws Exception {

        ActivatePackagingCommand command = (ActivatePackagingCommand) context.getBean("activatePackagingCommand");
        command.setUpdateUserId(getSessionUser().UserId);
        command.setPackagingId(packagingId);
        packagingService.ActivatePackaging(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.PackagingUpdate})
    @RequestMapping(value = "/su/packaging/update/{packagingId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int packagingId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/packaging/update");
        GetPackagingRequest request = (GetPackagingRequest) context.getBean("getProductFeatureRequest");
        request.setPackagingId(packagingId);
        PackagingDto dto = packagingService.GetPackagingDto(request);
        PackagingUpdateViewModel model = new PackagingUpdateViewModel();
        model.setIsActive(dto.getIsActive());
        model.setResourceKey(dto.getResourceKey());
        model.setName(dto.getName());
        model.setPackagingId(dto.getPackagingId());
        mav.addObject("packaging", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.PackagingUpdate})
    @RequestMapping(value = "/su/packaging/update", method = RequestMethod.POST)
    public ModelAndView updateProductCategory(@Valid @ModelAttribute("productCategory") PackagingUpdateViewModel packagingUpdateViewModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/packaging/update");
        if (result.hasErrors()) {
            mav.addObject("packaging", packagingUpdateViewModel);
            return mav;
        }
        UpdatePackagingCommand command = (UpdatePackagingCommand) context.getBean("updateProductFeatureCommand");
        command.setResourceKey(packagingUpdateViewModel.getResourceKey());
        command.setName(packagingUpdateViewModel.getName());
        command.setIsActive(packagingUpdateViewModel.getIsActive());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setPackagingId(packagingUpdateViewModel.getPackagingId());
        packagingService.UpdatePackaging(command);
        mav.addObject("packaging", packagingUpdateViewModel);
        mav.addObject("message", context.getMessage("message.packagingUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


}
