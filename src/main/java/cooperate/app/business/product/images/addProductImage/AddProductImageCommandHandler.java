package cooperate.app.business.product.images.addProductImage;

import cooperate.app.data.write.ProductImageRepository;
import cooperate.app.entity.ProductImage;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProductImageCommandHandler implements IHandleCommand<AddProductImageCommand> {
    @Autowired
    ProductImageRepository productImageRepository;

    public void Handle(AddProductImageCommand command) throws Exception {
        ProductImage productImage = new ProductImage();
        productImage.setCreateDate(new Timestamp(System.currentTimeMillis()));
        productImage.setCreateUserId(command.getCreateUserId());
        productImage.setIsActive(command.isActive());
        productImage.setProductId(command.getProductId());
        productImage.setOrderNo(command.getOrderNo());
        productImage.setAltText(command.getAltText());
        productImage.setImageUrl(command.getImageUrl());
        productImageRepository.create(productImage);
    }
}
