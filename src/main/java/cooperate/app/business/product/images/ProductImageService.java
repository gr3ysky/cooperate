package cooperate.app.business.product.images;

import cooperate.app.ServiceBase;
import cooperate.app.business.product.images.activateProductImage.ActivateProductImageCommand;
import cooperate.app.business.product.images.addProductImage.AddProductImageCommand;
import cooperate.app.business.product.images.getProductImage.GetProductImageRequest;
import cooperate.app.business.product.images.listProductImages.ListProductImagesRequestHandler;
import cooperate.app.business.product.images.updateProductImage.UpdateProductImageCommand;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productImage.ProductImageDto;
import cooperate.infrastructure.dto.productImage.ProductImageFilterDto;
import cooperate.infrastructure.dto.productImage.ProductImageListDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductImageService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public ListResponse<ProductImageListDto> ListProductImages(ListRequest<ProductImageFilterDto, ProductImageListDto> request) throws Exception {
        request.setHandler((ListProductImagesRequestHandler) context.getBean("listProductImagesRequestHandler"));
        return mediator.request(request);

    }

    @Transactional(readOnly = true)
    public ProductImageDto GetProductImageDto(GetProductImageRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void AddProductImage(AddProductImageCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProductImage(ActivateProductImageCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void UpdateProductImage(UpdateProductImageCommand command) throws Exception {
        mediator.send(command);
    }
}
