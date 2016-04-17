package cooperate.app.business.productFeature.getProductFeature;

import cooperate.app.data.read.ProductFeatureReadRepository;
import cooperate.app.entity.ProductFeature;
import cooperate.infrastructure.dto.productFeature.ProductFeatureDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductFeatureRequetsHandler implements IHandleRequest<GetProductFeatureRequest, ProductFeatureDto> {
    @Autowired
    ProductFeatureReadRepository productFeatureReadRepository;

    public ProductFeatureDto Handle(GetProductFeatureRequest request) throws Exception {
        ProductFeature productFeature = productFeatureReadRepository.findOne(request.getProductFeatureId());
        if (productFeature == null)
            throw new CoopException("error.productFeatureNotFound");
        ProductFeatureDto dto = new ProductFeatureDto();
        dto.setProductFeatureId(productFeature.getProductFeatureId());
        dto.setIsActive(productFeature.getIsActive());
        dto.setName(productFeature.getName());
        dto.setNameResourceKey(productFeature.getNameResourceKey());
        dto.setTitle(productFeature.getTitle());
        dto.setTitleResourceKey(productFeature.getTitleResourceKey());
        dto.setImageUrl(productFeature.getImageUrl());
        return dto;
    }
}
