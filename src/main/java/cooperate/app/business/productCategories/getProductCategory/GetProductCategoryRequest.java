package cooperate.app.business.productCategories.getProductCategory;

import cooperate.infrastructure.dto.productCategory.ProductCategoryDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductCategoryRequest implements IRequest<GetProductCategoryRequest, ProductCategoryDto> {

    @Autowired
    GetProductCategoryRequestHandler handler;

    private int productCategoryId;

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public IHandleRequest<GetProductCategoryRequest, ProductCategoryDto> getHandler() {
        return handler;
    }
}
