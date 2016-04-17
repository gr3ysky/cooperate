package cooperate.app.business.saleType.activateSaleType;


import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateSaleTypeCommand implements ICommand {
    @Autowired
    ActivateSaleTypeCommandHandler handler;
    private int saleTypeId;
    private int updateUserId;

    public IHandleCommand<ActivateSaleTypeCommand> getHandler() {
        return handler;
    }

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }
}
