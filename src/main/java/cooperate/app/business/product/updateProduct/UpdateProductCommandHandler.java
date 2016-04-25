package cooperate.app.business.product.updateProduct;

import cooperate.app.data.read.ProductReadRepository;
import cooperate.app.data.write.ProductRepository;
import cooperate.app.entity.Product;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateProductCommandHandler implements IHandleCommand<UpdateProductCommand> {
    @Autowired
    ProductReadRepository productReadRepository;

    @Autowired
    ProductRepository productRepository;

    public void Handle(UpdateProductCommand command) throws Exception {
        Product product = productReadRepository.findOne(command.getProductId());
        if (product == null)
            throw new CoopException("error.productNotFound");
        product.setIsActive(command.isActive());
        product.setUpdateUserId(command.getUpdateUserId());
        product.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        product.setName(WordUtils.capitalizeFully(command.getName()));
        product.setProducerId(command.getProducerId());
        product.setUnitId(command.getUnitId());
        product.setIsPrePayed(command.isPrePayed());
        product.setSaleTypeId(command.getSaleTypeId());
        product.setPackagingId(command.getPackagingId());
        product.setTags(command.getTags());
        product.setUnitPrice(command.getUnitPrice());
        product.setProductCategoryId(command.getProductCategoryId());
        product.setStockCount(command.getStockCount());

        productRepository.update(product);
    }
}
