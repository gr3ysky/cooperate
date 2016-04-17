package cooperate.app.business.productFeature.activateProductFeature;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateProductFeatureCommand implements ICommand {

    @Autowired
    ActivateProductFeatureCommandHandler handler;
    private int productFeatureId;
    private int updateUserId;

    public IHandleCommand<ActivateProductFeatureCommand> getHandler() {
        return handler;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
    }
}
