package cooperate.app.business.productFeature.listProductFeatures;

import cooperate.app.data.read.ProductFeatureReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productFeature.ProductFeatureFilterDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProductFeaturesRequestHandler implements IHandleRequest<ListRequest<ProductFeatureFilterDto, ProductFeatureListDto>, ListResponse<ProductFeatureListDto>> {

    @Autowired
    ProductFeatureReadRepository productFeatureReadRepository;

    public ListResponse<ProductFeatureListDto> Handle(ListRequest<ProductFeatureFilterDto, ProductFeatureListDto> request) throws Exception {
        return productFeatureReadRepository.ListProductFeatures(request);
    }
}
