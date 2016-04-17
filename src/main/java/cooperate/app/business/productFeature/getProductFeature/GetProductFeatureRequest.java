package cooperate.app.business.productFeature.getProductFeature;

import cooperate.infrastructure.dto.productFeature.ProductFeatureDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductFeatureRequest implements IRequest<GetProductFeatureRequest, ProductFeatureDto> {
    @Autowired
    GetProductFeatureRequetsHandler handler;
    private int productFeatureId;

    public IHandleRequest<GetProductFeatureRequest, ProductFeatureDto> getHandler() {
        return handler;
    }

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
    }
}
