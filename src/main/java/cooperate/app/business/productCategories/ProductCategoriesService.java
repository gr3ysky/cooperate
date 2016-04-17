package cooperate.app.business.productCategories;

import cooperate.app.ServiceBase;
import cooperate.app.business.productCategories.listProductCategories.ListProductCategoriesRequestHandler;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productCategory.ProductCategoryDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryFilterDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryListDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IRequest;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCategoriesService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public ListResponse<ProductCategoryListDto> ListProductCategories(ListRequest<ProductCategoryFilterDto, ProductCategoryListDto> request) throws Exception {
        request.setHandler((ListProductCategoriesRequestHandler) context.getBean("listProductCategoriesRequestHandler"));
        return mediator.request(request);
    }

    @Transactional
    public void AddProductCommand(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProductCategory(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public ProductCategoryDto GetProductCategoryDto(IRequest request) throws Exception {
        return mediator.request(request);

    }

    @Transactional
    public void UpdateProduct(ICommand command) throws Exception {
        mediator.send(command);
    }
}
