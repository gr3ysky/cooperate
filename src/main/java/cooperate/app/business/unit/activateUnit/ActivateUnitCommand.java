package cooperate.app.business.unit.activateUnit;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateUnitCommand implements ICommand {

    @Autowired
    ActivateUnitCommandHandler handler;

    private int unitId;
    private int updateUserId;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public IHandleCommand<ActivateUnitCommand> getHandler() {
        return handler;
    }
}
