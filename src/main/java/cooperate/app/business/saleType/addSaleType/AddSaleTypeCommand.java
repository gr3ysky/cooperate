package cooperate.app.business.saleType.addSaleType;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSaleTypeCommand implements ICommand {
    @Autowired
    AddSaleTypeCommandHandler handler;
    private String name;
    private String resourceKey;
    private boolean isActive;
    private int createUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }


    public IHandleCommand<AddSaleTypeCommand> getHandler() {
        return handler;
    }
}
