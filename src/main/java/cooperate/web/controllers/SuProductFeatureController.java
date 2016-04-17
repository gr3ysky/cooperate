package cooperate.web.controllers;

import cooperate.app.business.productFeature.ProductFeatureService;
import cooperate.app.business.productFeature.activateProductFeature.ActivateProductFeatureCommand;
import cooperate.app.business.productFeature.addProductFeature.AddProductFeatureCommand;
import cooperate.app.business.productFeature.getProductFeature.GetProductFeatureRequest;
import cooperate.app.business.productFeature.updateProductFeature.UpdateProductFeatureCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productFeature.ProductFeatureDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureFilterDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureListDto;
import cooperate.web.core.FileUtility;
import cooperate.web.core.FileUtilityBuilder;
import cooperate.web.core.HasPermission;
import cooperate.web.core.ImageValidator;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.productFeature.ProductFatureCreateViewModel;
import cooperate.web.viewmodels.productFeature.ProductFeatureUpdateViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class SuProductFeatureController extends BaseController {
    @Autowired
    ProductFeatureService service;

    @HasPermission(to = {PermissionConstants.ProductFeatureIndex})
    @RequestMapping("/su/product-feature")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/productFeature/index");
        mav.addObject("pageDescription", context.getMessage("page.description.productFeature.index", null, Locale.getDefault()));
        return mav;
    }

    @HasPermission(to = {PermissionConstants.SaleTypeIndex})
    @RequestMapping(value = "/su/product-feature/list")
    public ResponseEntity<ListResponse<ProductFeatureListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProductFeatureFilterDto, ProductFeatureListDto> request, @ModelAttribute("filter") ProductFeatureFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProductFeatureListDto> response = service.ListProductFeatures(request);
        return new ResponseEntity<ListResponse<ProductFeatureListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductFeatureCreate})
    @RequestMapping(value = "/su/product-feature/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productFeature/create");
        mav.addObject("productFeature", new ProductFatureCreateViewModel());
        return mav;

    }

    @HasPermission(to = {PermissionConstants.ProductFeatureCreate})
    @RequestMapping(value = "/su/product-feature/create", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProductFeature(@Valid @ModelAttribute("productFeature") ProductFatureCreateViewModel productFatureCreateViewModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productFeature/create");
        if (result.hasErrors()) {
            mav.addObject("prductFeature", productFatureCreateViewModel);
            return mav;
        }
        String imageUrl = null;
        if (!productFatureCreateViewModel.getImage().isEmpty() && ImageValidator.isValid(productFatureCreateViewModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(productFatureCreateViewModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }
        AddProductFeatureCommand command = (AddProductFeatureCommand) context.getBean("addProductFeatureCommand");
        command.setNameResourceKey(productFatureCreateViewModel.getName());
        command.setName(productFatureCreateViewModel.getName());
        command.setTitleResourceKey(productFatureCreateViewModel.getTitleResourceKey());
        command.setTitle(productFatureCreateViewModel.getTitle());
        command.setActive(productFatureCreateViewModel.getIsActive());
        command.setCreateUserId(getSessionUser().UserId);
        command.setImageUrl(imageUrl);
        service.AddProductFeature(command);
        mav.addObject("saleType", new ProductFatureCreateViewModel());
        mav.addObject("message", context.getMessage("message.productFeatureAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductFeatureUpdate})
    @RequestMapping(value = "/su/product-feature/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int productFeatureId) throws Exception {

        ActivateProductFeatureCommand command = (ActivateProductFeatureCommand) context.getBean("activateProductFeatureCommand");
        command.setUpdateUserId(getSessionUser().UserId);
        command.setProductFeatureId(productFeatureId);
        service.ActivateProductFeature(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductFeatureUpdate})
    @RequestMapping(value = "/su/product-feature/update/{productFeatureId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int productFeatureId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productFeature/update");
        GetProductFeatureRequest request = (GetProductFeatureRequest) context.getBean("getProductFeatureRequest");
        request.setProductFeatureId(productFeatureId);
        ProductFeatureDto dto = service.GetProductFeaturegDto(request);
        ProductFeatureUpdateViewModel model = new ProductFeatureUpdateViewModel();
        model.setIsActive(dto.getIsActive());
        model.setNameResourceKey(dto.getNameResourceKey());
        model.setName(dto.getName());
        model.setTitleResourceKey(dto.getTitle());
        model.setTitle(dto.getTitle());
        model.setProductFeatureId(dto.getProductFeatureId());
        model.setImageUrl(dto.getImageUrl());
        mav.addObject("productFeature", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductFeatureUpdate})
    @RequestMapping(value = "/su/product-feature/update", method = RequestMethod.POST)
    public ModelAndView updateProductFeature(@Valid @ModelAttribute("productFeature") ProductFeatureUpdateViewModel productFeatureUpdateViewModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productFeature/update");
        if (result.hasErrors()) {
            mav.addObject("productFeature", productFeatureUpdateViewModel);
            return mav;
        }
        String imageUrl = null;
        if (!productFeatureUpdateViewModel.getImage().isEmpty() && ImageValidator.isValid(productFeatureUpdateViewModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(productFeatureUpdateViewModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }

        UpdateProductFeatureCommand command = (UpdateProductFeatureCommand) context.getBean("updateProductFeatureCommand");
        command.setNameResourceKey(productFeatureUpdateViewModel.getNameResourceKey());
        command.setName(productFeatureUpdateViewModel.getName());
        command.setTitleResourceKey(productFeatureUpdateViewModel.getTitleResourceKey());
        command.setTitle(productFeatureUpdateViewModel.getTitle());
        command.setIsActive(productFeatureUpdateViewModel.getIsActive());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setProductFeatureId(productFeatureUpdateViewModel.getProductFeatureId());
        command.setImageUrl(imageUrl);
        service.UpdateProductFeature(command);
        mav.addObject("productFeature", productFeatureUpdateViewModel);
        mav.addObject("message", context.getMessage("message.productFeatureUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


}
