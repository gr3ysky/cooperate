package cooperate.app.business.product.images.getProductImage;

import cooperate.infrastructure.dto.productImage.ProductImageDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductImageRequest implements IRequest<GetProductImageRequest, ProductImageDto> {

    @Autowired
    GetProductImageRequestHandler handler;

    private int productId;
    private int productImageId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    public IHandleRequest<GetProductImageRequest, ProductImageDto> getHandler() {
        return handler;
    }
}
