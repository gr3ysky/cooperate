package cooperate.app.business.productCategories.updateProductCategory;

import cooperate.app.data.read.ProductCategoriesReadRepository;
import cooperate.app.data.write.ProductCategoryRepository;
import cooperate.app.entity.ProductCategory;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCategoryCommandHandler implements IHandleCommand<UpdateProductCategoryCommand> {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductCategoriesReadRepository productCategoriesReadRepository;

    public void Handle(UpdateProductCategoryCommand command) throws Exception {
        ProductCategory pc = productCategoriesReadRepository.findOne(command.getProductCategoryId());
        if (pc == null)
            throw new CoopException("error.productCategoryNotFound");
        pc.setIsActive(command.getIsActive());
        pc.setUpdateUserId(command.getUpdateUserId());
        pc.setName(WordUtils.capitalizeFully(command.getName()));
        pc.setIsActive(command.getIsActive());
        pc.setResourceKey(command.getResourceKey());
        productCategoryRepository.update(pc);
    }
}
