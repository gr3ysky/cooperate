package cooperate.app.business.productCategories.activateProductCategory;

import cooperate.app.data.read.ProductCategoriesReadRepository;
import cooperate.app.data.write.ProductCategoryRepository;
import cooperate.app.entity.ProductCategory;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProductCategoryCommandHandler implements IHandleCommand<ActivateProductCategoryCommand> {

    @Autowired
    ProductCategoriesReadRepository productCategoriesReadRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public void Handle(ActivateProductCategoryCommand command) throws Exception {

        ProductCategory pc = productCategoriesReadRepository.findOne(command.getProductCategoryId());
        if (pc == null)
            throw new CoopException("error.productCategoryNotFound");

        pc.setIsActive(!pc.getIsActive());
        pc.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        pc.setUpdateUserId(command.getUpdateUserId());
        productCategoryRepository.update(pc);
    }
}
