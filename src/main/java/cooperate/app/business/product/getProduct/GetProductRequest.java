package cooperate.app.business.product.getProduct;

import cooperate.infrastructure.dto.product.ProductDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductRequest implements IRequest<GetProductRequest, ProductDto> {

    @Autowired
    GetProductRequestHandler handler;

    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public IHandleRequest<GetProductRequest, ProductDto> getHandler() {
        return handler;
    }
}
