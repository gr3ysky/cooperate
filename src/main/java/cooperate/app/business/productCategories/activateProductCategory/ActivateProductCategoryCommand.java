package cooperate.app.business.productCategories.activateProductCategory;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateProductCategoryCommand implements ICommand {

    @Autowired
    ActivateProductCategoryCommandHandler handler;
    private int productCategoryId;
    private int updateUserId;

    public IHandleCommand<ActivateProductCategoryCommand> getHandler() {
        return handler;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }


    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}
