package cooperate.app.business.productFeature;

import cooperate.app.ServiceBase;
import cooperate.app.business.productFeature.getProductFeature.GetProductFeatureRequest;
import cooperate.app.business.productFeature.listProductFeatures.ListProductFeaturesRequestHandler;
import cooperate.app.data.read.ProductFeatureReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.productFeature.ProductFeatureDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureFilterDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureListDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IRequest;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductFeatureService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Autowired
    ProductFeatureReadRepository productFeatureReadRepository;

    @Transactional(readOnly = true)
    public List<SelectListItem> getProductFeaturesDropDown() throws Exception {
        return productFeatureReadRepository.getProductFeaturesDropDown();
    }

    @Transactional
    public void AddProductFeature(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProductFeature(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public ProductFeatureDto GetProductFeaturegDto(IRequest<GetProductFeatureRequest, ProductFeatureDto> request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void UpdateProductFeature(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public ListResponse<ProductFeatureListDto> ListProductFeatures(ListRequest<ProductFeatureFilterDto, ProductFeatureListDto> request) throws Exception {
        request.setHandler((ListProductFeaturesRequestHandler) context.getBean("listProductFeaturesRequestHandler"));
        return mediator.request(request);
    }

}
