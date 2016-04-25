package cooperate.app.business.product.images.updateProductImage;

import cooperate.app.data.read.ProductImageReadRepository;
import cooperate.app.data.write.ProductImageRepository;
import cooperate.app.entity.ProductImage;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateProductImageCommandHandler implements IHandleCommand<UpdateProductImageCommand> {
    @Autowired
    ProductImageReadRepository producerImageReadRepository;

    @Autowired
    ProductImageRepository producerImageRepository;

    public void Handle(UpdateProductImageCommand command) throws Exception {
        ProductImage productImage = producerImageReadRepository.findOne(command.getProductImageId());
        if (productImage == null || productImage.getProductId() != command.getProductId())
            throw new CoopException("error.productImageNotFound");
        productImage.setCreateDate(new Timestamp(System.currentTimeMillis()));
        productImage.setCreateUserId(command.getUpdateUserId());
        productImage.setIsActive(command.isActive());
        productImage.setOrderNo(command.getOrderNo());
        productImage.setAltText(command.getAltText());
        if (command.getImageUrl() != null)
            productImage.setImageUrl(command.getImageUrl());
        producerImageRepository.update(productImage);
    }
}
