package cooperate.app.business.productCategories.addProductCategory;

import cooperate.app.data.write.ProductCategoryRepository;
import cooperate.app.entity.ProductCategory;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProductCategoryCommandHandler implements IHandleCommand<AddProductCategoryCommand> {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public void Handle(AddProductCategoryCommand command) throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCreateDate(new Timestamp(System.currentTimeMillis()));
        productCategory.setCreateUserId(command.getCreateUserId());
        productCategory.setIsActive(command.isActive());
        productCategory.setName(WordUtils.capitalizeFully(command.getName()));
        productCategory.setResourceKey(command.getResourceKey());
        productCategoryRepository.create(productCategory);
    }
}
