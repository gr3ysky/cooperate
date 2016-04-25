package cooperate.app.business.product.addProduct;

import cooperate.app.data.write.ProductRepository;
import cooperate.app.entity.Product;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProductCommandHandler implements IHandleCommand<AddProductCommand> {
    @Autowired
    ProductRepository productRepository;

    public void Handle(AddProductCommand command) throws Exception {
        Product product = new Product();
        product.setCreateDate(new Timestamp(System.currentTimeMillis()));
        product.setCreateUserId(command.getCreateUserId());
        product.setIsActive(command.isActive());
        product.setName(WordUtils.capitalizeFully(command.getName()));
        product.setProducerId(command.getProducerId());
        product.setUnitId(command.getUnitId());
        product.setIsPrePayed(command.isPrePayed());
        product.setSaleTypeId(command.getSaleTypeId());
        product.setStockCount(command.getStockCount());
        product.setPackagingId(command.getPackagingId());
        product.setTags(command.getTags());
        product.setUnitPrice(command.getUnitPrice());
        product.setProductCategoryId(command.getProductCategoryId());
        productRepository.create(product);
    }
}
