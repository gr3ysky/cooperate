package cooperate.app.business.product.activateProduct;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateProductCommand implements ICommand {

    @Autowired
    ActivateProductCommandHandler handler;
    private int productId;
    private int updateUserId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public IHandleCommand<ActivateProductCommand> getHandler() {
        return handler;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

}
