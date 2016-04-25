package cooperate.app.business.product.images.listProductImages;

import cooperate.app.data.read.ProductImageReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productImage.ProductImageFilterDto;
import cooperate.infrastructure.dto.productImage.ProductImageListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProductImagesRequestHandler implements IHandleRequest<ListRequest<ProductImageFilterDto, ProductImageListDto>, ListResponse<ProductImageListDto>> {
    @Autowired
    private ProductImageReadRepository productImageReadRepository;

    public ListResponse<ProductImageListDto> Handle(ListRequest<ProductImageFilterDto, ProductImageListDto> request) throws Exception {
        return productImageReadRepository.ListProductImages(request);
    }
}
