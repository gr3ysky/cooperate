package cooperate.app.business.product.images.getProductImage;

import cooperate.app.data.read.ProductImageReadRepository;
import cooperate.app.entity.ProductImage;
import cooperate.infrastructure.dto.productImage.ProductImageDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProductImageRequestHandler implements IHandleRequest<GetProductImageRequest, ProductImageDto> {
    @Autowired
    ProductImageReadRepository productImageReadRepository;

    public ProductImageDto Handle(GetProductImageRequest request) throws Exception {
        ProductImage productImage = productImageReadRepository.findOne(request.getProductImageId());
        if (productImage == null || productImage.getProductId() != request.getProductId())
            throw new CoopException("error.productImageNotFound");
        ProductImageDto dto = new ProductImageDto();
        dto.setProductId(productImage.getProductId());
        dto.setIsActive(productImage.getIsActive());
        dto.setImageUrl(productImage.getImageUrl());
        dto.setOrderNo(productImage.getOrderNo());
        dto.setAltText(productImage.getAltText());
        dto.setProductImageId(productImage.getProductImageId());
        return dto;
    }
}
