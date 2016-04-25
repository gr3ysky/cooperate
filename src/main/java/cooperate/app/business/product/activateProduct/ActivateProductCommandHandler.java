package cooperate.app.business.product.activateProduct;

import cooperate.app.data.read.ProductReadRepository;
import cooperate.app.data.write.ProductRepository;
import cooperate.app.entity.Product;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProductCommandHandler implements IHandleCommand<ActivateProductCommand> {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductReadRepository productReadRepository;

    public void Handle(ActivateProductCommand command) throws Exception {

        Product p = productReadRepository.findOne(command.getProductId());
        if (p == null)
            throw new CoopException("error.productNotFound");

        p.setIsActive(!p.getIsActive());
        p.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        p.setUpdateUserId(command.getUpdateUserId());
        productRepository.update(p);
    }
}
