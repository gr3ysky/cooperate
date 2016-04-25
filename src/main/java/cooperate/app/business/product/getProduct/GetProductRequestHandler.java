package cooperate.app.business.product.getProduct;

import cooperate.app.data.read.ProductReadRepository;
import cooperate.app.entity.Product;
import cooperate.infrastructure.dto.product.ProductDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductRequestHandler implements IHandleRequest<GetProductRequest, ProductDto> {
    @Autowired
    ProductReadRepository productReadRepository;

    public ProductDto Handle(GetProductRequest request) throws Exception {
        Product p = productReadRepository.findOne(request.getProductId());
        if (p == null)
            throw new CoopException("error.productNotFound");
        ProductDto dto = new ProductDto();
        dto.setProducerId(p.getProducerId());
        dto.setActive(p.getIsActive());
        dto.setName(p.getName());
        dto.setUnitPrice(p.getUnitPrice());
        dto.setProducerId(p.getProducerId());
        dto.setProductId(p.getProductId());
        dto.setProductCategoryId(p.getProductCategoryId());
        dto.setPackagingId(p.getPackagingId());
        dto.setTags(p.getTags());
        dto.setPrePayed(p.getIsPrePayed());
        dto.setSaleTypeId(p.getSaleTypeId());
        dto.setStockCount(p.getStockCount());
        return dto;
    }
}
