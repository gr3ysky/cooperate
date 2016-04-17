package cooperate.app.business.unit.updateUnit;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUnitCommand implements ICommand {
    @Autowired
    UpdateUnitCommandHandler handler;
    private int unitId;
    private String name;
    private String abbrevation;
    private String resourceKey;
    private int updateUserId;
    private boolean isActive;

    public IHandleCommand<UpdateUnitCommand> getHandler() {
        return handler;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getAbbrevation() {
        return abbrevation;
    }

    public void setAbbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
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
}
