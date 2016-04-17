package cooperate.app.business.packaging.updatePackaging;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePackagingCommand implements ICommand {
    @Autowired
    UpdatePackagingCommandHandler handler;
    private int packagingId;
    private String name;
    private String resourceKey;
    private int updateUserId;
    private boolean isActive;

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String fname) {
        this.name = fname;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }


    public IHandleCommand<UpdatePackagingCommand> getHandler() {
        return handler;
    }


}
