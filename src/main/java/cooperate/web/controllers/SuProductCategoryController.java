package cooperate.web.controllers;

import cooperate.app.business.productCategories.ProductCategoriesService;
import cooperate.app.business.productCategories.activateProductCategory.ActivateProductCategoryCommand;
import cooperate.app.business.productCategories.addProductCategory.AddProductCategoryCommand;
import cooperate.app.business.productCategories.getProductCategory.GetProductCategoryRequest;
import cooperate.app.business.productCategories.updateProductCategory.UpdateProductCategoryCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productCategory.ProductCategoryDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryFilterDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryListDto;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.ProductCategoryCreateModel;
import cooperate.web.viewmodels.ProductCategoryUpdateModel;
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
public class SuProductCategoryController extends BaseController {

    @Autowired
    ProductCategoriesService productCategoriesService;

    @HasPermission(to = {PermissionConstants.ProductCategoriesIndex})
    @RequestMapping("/su/product-categories")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("superuser/productCategories/index");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesIndex})
    @RequestMapping(value = "/su/product-categories/list")
    public ResponseEntity<ListResponse<ProductCategoryListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProductCategoryFilterDto, ProductCategoryListDto> request, @ModelAttribute("filter") ProductCategoryFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProductCategoryListDto> response = productCategoriesService.ListProductCategories(request);
        return new ResponseEntity<ListResponse<ProductCategoryListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesCreate})
    @RequestMapping(value = "/su/product-categories/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productCategories/create");
        mav.addObject("productCategory", new ProductCategoryCreateModel());
        return mav;

    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesCreate})
    @RequestMapping(value = "/su/product-categories/create", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProductCategory(@Valid @ModelAttribute("productCategory") ProductCategoryCreateModel productCategoryCreateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productCategories/create");
        if (result.hasErrors()) {
            mav.addObject("productCategory", productCategoryCreateModel);
            return mav;
        }
        AddProductCategoryCommand command = (AddProductCategoryCommand) context.getBean("addProductCategoryCommand");
        command.setResourceKey(productCategoryCreateModel.getResourceKey());
        command.setName(productCategoryCreateModel.getName());
        command.setActive(productCategoryCreateModel.getIsActive());
        command.setCreateUserId(getSessionUser().UserId);
        productCategoriesService.AddProductCommand(command);
        mav.addObject("productCategory", new ProductCategoryCreateModel());
        mav.addObject("message", context.getMessage("message.productCategoryAdded", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesUpdate})
    @RequestMapping(value = "/su/product-categories/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int productCategoryId) throws Exception {

        ActivateProductCategoryCommand command = (ActivateProductCategoryCommand) context.getBean("activateProductCategoryCommand");
        command.setUpdateUserId(getSessionUser().UserId);
        command.setProductCategoryId(productCategoryId);
        productCategoriesService.ActivateProductCategory(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesUpdate})
    @RequestMapping(value = "/su/product-categories/update/{productCategoryId}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int productCategoryId) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productCategories/update");
        GetProductCategoryRequest request = (GetProductCategoryRequest) context.getBean("getProductCategoryRequest");
        request.setProductCategoryId(productCategoryId);
        ProductCategoryDto dto = productCategoriesService.GetProductCategoryDto(request);
        ProductCategoryUpdateModel model = new ProductCategoryUpdateModel();
        model.setIsActive(dto.getIsActive());
        model.setResourceKey(dto.getResourceKey());
        model.setName(dto.getName());
        model.setProductCategoryId(dto.getProductCategoryId());
        mav.addObject("productCategory", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductCategoriesUpdate})
    @RequestMapping(value = "/su/product-categories/update", method = RequestMethod.POST)
    public ModelAndView updateProductCategory(@Valid @ModelAttribute("productCategory") ProductCategoryUpdateModel productCategory, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("superuser/productCategories/update");
        if (result.hasErrors()) {
            mav.addObject("productCategory", productCategory);
            return mav;
        }
        UpdateProductCategoryCommand command = (UpdateProductCategoryCommand) context.getBean("updateProductCategoryCommand");
        command.setResourceKey(productCategory.getResourceKey());
        command.setName(productCategory.getName());
        command.setIsActive(productCategory.getIsActive());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setProductCategoryId(productCategory.getProductCategoryId());
        productCategoriesService.UpdateProduct(command);
        mav.addObject("productCategory", productCategory);
        mav.addObject("message", context.getMessage("message.productCategoryUpdated", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

}
