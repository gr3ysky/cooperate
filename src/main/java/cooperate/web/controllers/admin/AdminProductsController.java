package cooperate.web.controllers.admin;

import cooperate.app.business.packaging.PackagingService;
import cooperate.app.business.producer.ProducerService;
import cooperate.app.business.product.ProductService;
import cooperate.app.business.product.activateProduct.ActivateProductCommand;
import cooperate.app.business.product.addProduct.AddProductCommand;
import cooperate.app.business.product.getProduct.GetProductRequest;
import cooperate.app.business.product.updateProduct.UpdateProductCommand;
import cooperate.app.business.productCategories.ProductCategoriesService;
import cooperate.app.business.saleType.SaleTypeService;
import cooperate.app.business.unit.UnitService;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.product.ProductDto;
import cooperate.infrastructure.dto.product.ProductFilterDto;
import cooperate.infrastructure.dto.product.ProductListDto;
import cooperate.web.controllers.BaseController;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.product.ProductCreateModel;
import cooperate.web.viewmodels.product.ProductUpdateModel;
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
public class AdminProductsController extends BaseController {

    @Autowired
    ProductService productService;

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping("/admin/products")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin/products/index");
        mav.addObject("pageDescription", context.getMessage("page.description.admin.products.index", null, Locale.getDefault()));
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/products/list")
    public ResponseEntity<ListResponse<ProductListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProductFilterDto, ProductListDto> request, @ModelAttribute("filter") ProductFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProductListDto> response = productService.ListProducts(request);
        return new ResponseEntity<ListResponse<ProductListDto>>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductCreate})
    @RequestMapping(value = "/admin/products/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("admin/products/create");
        ProductCreateModel model = new ProductCreateModel();
        FillDropDowns(mav);
        mav.addObject("product", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductUpdate})
    @RequestMapping(value = "/admin/products/update/{productId}")
    public ModelAndView update(@PathVariable int productId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/products/update");
        GetProductRequest request = (GetProductRequest) context.getBean("getProductRequest");
        request.setProductId(productId);
        ProductDto dto = productService.GetProductDto(request);
        ProductUpdateModel model = new ProductUpdateModel();
        model.setProductId(productId);
        model.setActive(dto.isActive());
        model.setName(dto.getName());
        model.setUnitPrice(dto.getUnitPrice());
        model.setUnitId(dto.getUnitId());
        model.setProducerId(dto.getUnitId());
        model.setSaleTypeId(dto.getSaleTypeId());
        model.setPackagingId(dto.getPackagingId());
        model.setPrePayed(dto.isPrePayed());
        model.setStockCount(dto.getStockCount());
        model.setProductCategoryId(dto.getProductCategoryId());
        model.setTags(dto.getTags());
        mav.addObject("product", model);
        FillDropDowns(mav);
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProductCreate})
    @RequestMapping(value = "/admin/products/createProduct", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProduct(@Valid @ModelAttribute("product") ProductCreateModel productCreateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("admin/products/create");
        FillDropDowns(mav);

        if (result.hasErrors()) {
            mav.addObject("product", productCreateModel);
            return mav;
        }
        AddProductCommand command = (AddProductCommand) context.getBean("addProductCommand");
        command.setActive(productCreateModel.isActive());
        command.setName(productCreateModel.getName());
        command.setCreateUserId(getSessionUser().UserId);
        command.setProducerId(productCreateModel.getProducerId());
        command.setProductCategoryId(productCreateModel.getProductCategoryId());
        command.setUnitPrice(productCreateModel.getUnitPrice());
        command.setUnitId(productCreateModel.getUnitId());
        command.setActive(productCreateModel.isActive());
        command.setPrePayed(productCreateModel.isPrePayed());
        command.setTags(productCreateModel.getTags());
        command.setSaleTypeId(productCreateModel.getSaleTypeId());
        command.setPackagingId(productCreateModel.getPackagingId());
        command.setStockCount(productCreateModel.getStockCount());
        productService.AddProduct(command);
        mav.addObject("product", new ProductCreateModel());
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

    private void FillDropDowns(ModelAndView mav) throws Exception {
        ProducerService producerService = (ProducerService) context.getBean("producerService");
        ProductCategoriesService productCategoriesService = (ProductCategoriesService) context.getBean("productCategoriesService");
        SaleTypeService saleTypeService = (SaleTypeService) context.getBean("saleTypeService");
        UnitService unitService = (UnitService) context.getBean("unitService");
        PackagingService packagingService = (PackagingService) context.getBean("packagingService");
        mav.addObject("producers", producerService.getProducersDropDown());
        mav.addObject("productCategories", productCategoriesService.getProductCategoriesDropDown());
        mav.addObject("saleTypes", saleTypeService.getSaleTypesDropDown());
        mav.addObject("units", unitService.getUnitsDropDown());
        mav.addObject("packagings", packagingService.getPackagingDropdown());
    }


    @HasPermission(to = {PermissionConstants.ProductDelete})
    @RequestMapping(value = "/admin/products/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int productId) throws Exception {
        ActivateProductCommand command = (ActivateProductCommand) context.getBean("activateProductCommand");
        command.setProductId(productId);
        command.setUpdateUserId(getSessionUser().UserId);
        productService.ActivateProduct(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductUpdate})
    @RequestMapping(value = "/admin/products/updateProduct", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView updateProduct(@Valid @ModelAttribute("product") ProductUpdateModel productUpdateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("admin/products/update");
        FillDropDowns(mav);
        if (result.hasErrors()) {
            mav.addObject("product", productUpdateModel);
            return mav;
        }
        UpdateProductCommand command = (UpdateProductCommand) context.getBean("updateProductCommand");
        command.setProductId(productUpdateModel.getProductId());
        command.setActive(productUpdateModel.isActive());
        command.setName(productUpdateModel.getName());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setProducerId(productUpdateModel.getProducerId());
        command.setProductCategoryId(productUpdateModel.getProductCategoryId());
        command.setUnitPrice(productUpdateModel.getUnitPrice());
        command.setUnitId(productUpdateModel.getUnitId());
        command.setActive(productUpdateModel.isActive());
        command.setPrePayed(productUpdateModel.isPrePayed());
        command.setTags(productUpdateModel.getTags());
        command.setSaleTypeId(productUpdateModel.getSaleTypeId());
        command.setPackagingId(productUpdateModel.getPackagingId());
        command.setStockCount(productUpdateModel.getStockCount());
        productService.UpdateProduct(command);
        mav.addObject("product", productUpdateModel);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }

}
