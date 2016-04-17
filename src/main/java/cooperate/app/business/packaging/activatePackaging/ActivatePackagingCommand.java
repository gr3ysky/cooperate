package cooperate.app.business.packaging.activatePackaging;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivatePackagingCommand implements ICommand {

    @Autowired
    ActivatePackagingCommandHandler handler;
    private int packagingId;
    private int updateUserId;

    public IHandleCommand<ActivatePackagingCommand> getHandler() {
        return handler;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }
}
