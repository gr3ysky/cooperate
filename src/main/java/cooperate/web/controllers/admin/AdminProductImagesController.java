package cooperate.web.controllers.admin;

import cooperate.app.business.product.images.ProductImageService;
import cooperate.app.business.product.images.activateProductImage.ActivateProductImageCommand;
import cooperate.app.business.product.images.addProductImage.AddProductImageCommand;
import cooperate.app.business.product.images.getProductImage.GetProductImageRequest;
import cooperate.app.business.product.images.updateProductImage.UpdateProductImageCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productImage.ProductImageDto;
import cooperate.infrastructure.dto.productImage.ProductImageFilterDto;
import cooperate.infrastructure.dto.productImage.ProductImageListDto;
import cooperate.web.controllers.BaseController;
import cooperate.web.core.FileUtility;
import cooperate.web.core.FileUtilityBuilder;
import cooperate.web.core.HasPermission;
import cooperate.web.core.ImageValidator;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.productImages.ProductImageCreateModel;
import cooperate.web.viewmodels.productImages.ProductImageUpdateModel;
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
public class AdminProductImagesController extends BaseController {
    @Autowired
    ProductImageService productImageService;

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping("/admin/product-images/{productId}")
    public ModelAndView index(@PathVariable int productId) {
        ModelAndView mav = new ModelAndView("admin/productImages/index");
        mav.addObject("productId", productId);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/list")
    public ResponseEntity<ListResponse<ProductImageListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProductImageFilterDto, ProductImageListDto> request, @ModelAttribute("filter") ProductImageFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProductImageListDto> response = productImageService.ListProductImages(request);
        return new ResponseEntity<ListResponse<ProductImageListDto>>(response, httpHeaders, HttpStatus.OK);

    }

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/create/{productId}")
    public ModelAndView create(@PathVariable int productId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/productImages/create");
        ProductImageCreateModel model = new ProductImageCreateModel();
        model.setProductId(productId);
        mav.addObject("productImage", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/update/{productId}/{productImageId}")
    public ModelAndView update(@PathVariable int productId, @PathVariable int productImageId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/productImages/update");
        GetProductImageRequest request = (GetProductImageRequest) context.getBean("getProductImageRequest");
        request.setProductId(productId);
        request.setProductImageId(productImageId);
        ProductImageDto dto = productImageService.GetProductImageDto(request);
        ProductImageUpdateModel model = new ProductImageUpdateModel();
        model.setProductImageId(productImageId);
        model.setImageUrl(dto.getImageUrl());
        model.setAltText(dto.getAltText());
        model.setOrderNo(dto.getOrderNo());
        model.setProductId(productId);
        model.setActive(dto.getIsActive());
        mav.addObject("productImage", model);
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/createProductImage", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProductImage(@Valid @ModelAttribute("productImage") ProductImageCreateModel productImageCreateModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("admin/productImages/create");
        if (result.hasErrors()) {
            mav.addObject("productImage", productImageCreateModel);
            return mav;
        }
        String imageUrl = null;
        if (!productImageCreateModel.getImage().isEmpty() && ImageValidator.isValid(productImageCreateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(productImageCreateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }

        AddProductImageCommand command = (AddProductImageCommand) context.getBean("addProductImageCommand");
        command.setActive(productImageCreateModel.isActive());
        command.setAltText(productImageCreateModel.getAltText());
        command.setCreateUserId(getSessionUser().UserId);
        command.setImageUrl(imageUrl);
        command.setProductId(productImageCreateModel.getProductId());
        command.setOrderNo(productImageCreateModel.getOrderNo());
        productImageService.AddProductImage(command);
        ProductImageCreateModel model = new ProductImageCreateModel();
        model.setProductId(productImageCreateModel.getProductId());
        mav.addObject("productImage", model);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int productImageId) throws Exception {
        ActivateProductImageCommand command = (ActivateProductImageCommand) context.getBean("activateProductImageCommand");
        command.setProductImageId(productImageId);
        command.setUpdateUserId(getSessionUser().UserId);
        productImageService.ActivateProductImage(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProductIndex})
    @RequestMapping(value = "/admin/product-images/updateProductImage", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView updateProductImage(@Valid @ModelAttribute("productImage") ProductImageUpdateModel productImageUpdateModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("admin/productImages/update");
        if (result.hasErrors()) {
            mav.addObject("productImage", productImageUpdateModel);
            return mav;
        }
        String imageUrl = null;
        if (!productImageUpdateModel.getImage().isEmpty() && ImageValidator.isValid(productImageUpdateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(productImageUpdateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }
        UpdateProductImageCommand command = (UpdateProductImageCommand) context.getBean("updateProductImageCommand");

        command.setActive(productImageUpdateModel.isActive());
        command.setAltText(productImageUpdateModel.getAltText());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setImageUrl(imageUrl);
        command.setProductImageId(productImageUpdateModel.getProductImageId());
        command.setOrderNo(productImageUpdateModel.getOrderNo());
        command.setProductId(productImageUpdateModel.getProductId());
        productImageService.UpdateProductImage(command);
        productImageUpdateModel.setImageUrl(imageUrl);
        mav.addObject("productImage", productImageUpdateModel);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }
}
