package cooperate.app.business.productCategories.getProductCategory;

import cooperate.app.data.read.ProductCategoriesReadRepository;
import cooperate.app.entity.ProductCategory;
import cooperate.infrastructure.dto.productCategory.ProductCategoryDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductCategoryRequestHandler implements IHandleRequest<GetProductCategoryRequest, ProductCategoryDto> {
    @Autowired
    ProductCategoriesReadRepository productCategoriesReadRepository;

    public ProductCategoryDto Handle(GetProductCategoryRequest request) throws Exception {
        ProductCategory pc = productCategoriesReadRepository.findOne(request.getProductCategoryId());
        if (pc == null)
            throw new CoopException("error.productCategoryNotFound");
        ProductCategoryDto dto = new ProductCategoryDto();
        dto.setProductCategoryId(pc.getProductCategoryId());
        dto.setIsActive(pc.getIsActive());
        dto.setName(pc.getName());
        dto.setResourceKey(pc.getResourceKey());
        return dto;
    }
}
