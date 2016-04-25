package cooperate.app.business.product.images.activateProductImage;

import cooperate.app.data.read.ProductImageReadRepository;
import cooperate.app.data.write.ProductImageRepository;
import cooperate.app.entity.ProductImage;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProductImageCommandHandler implements IHandleCommand<ActivateProductImageCommand> {

    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductImageReadRepository productImageReadRepository;

    public void Handle(ActivateProductImageCommand command) throws Exception {

        ProductImage productImage = productImageReadRepository.findOne(command.getProductImageId());
        if (productImage == null)
            throw new CoopException("error.productImageNotFound");

        productImage.setIsActive(!productImage.getIsActive());
        productImage.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        productImage.setUpdateUserId(command.getUpdateUserId());
        productImageRepository.update(productImage);
    }
}
