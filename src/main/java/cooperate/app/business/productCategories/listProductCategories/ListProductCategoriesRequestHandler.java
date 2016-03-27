package cooperate.app.business.productCategories.listProductCategories;

import cooperate.app.data.read.ProductCategoriesReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productCategory.ProductCategoryFilterDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProductCategoriesRequestHandler implements IHandleRequest<ListRequest<ProductCategoryFilterDto, ProductCategoryListDto>, ListResponse<ProductCategoryListDto>> {
    @Autowired
    private ProductCategoriesReadRepository productCategoriesReadRepository;

    public ListResponse<ProductCategoryListDto> Handle(ListRequest<ProductCategoryFilterDto, ProductCategoryListDto> request) throws Exception {
        return productCategoriesReadRepository.ListProductCategories(request);
    }
}
