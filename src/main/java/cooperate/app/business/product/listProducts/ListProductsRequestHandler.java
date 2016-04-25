package cooperate.app.business.product.listProducts;

import cooperate.app.data.read.ProductReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.product.ProductFilterDto;
import cooperate.infrastructure.dto.product.ProductListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProductsRequestHandler implements IHandleRequest<ListRequest<ProductFilterDto, ProductListDto>, ListResponse<ProductListDto>> {
    @Autowired
    private ProductReadRepository productReadRepository;

    public ListResponse<ProductListDto> Handle(ListRequest<ProductFilterDto, ProductListDto> request) throws Exception {
        return productReadRepository.ListProducts(request);
    }
}
