package cooperate.app.business.saleType.updateSaleType;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSaleTypeCommand implements ICommand {
    @Autowired
    UpdateSaleTypeCommandHandler handler;
    private int saleTypeId;
    private String name;
    private String resourceKey;
    private int updateUserId;
    private boolean isActive;

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
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

    public IHandleCommand<UpdateSaleTypeCommand> getHandler() {
        return handler;
    }
}
