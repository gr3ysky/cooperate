package cooperate.app.business.unit.activateUnit;

import cooperate.app.data.read.UnitReadRepository;
import cooperate.app.data.write.UnitRepository;
import cooperate.app.entity.Unit;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateUnitCommandHandler implements IHandleCommand<ActivateUnitCommand> {

    @Autowired
    UnitReadRepository unitReadRepository;
    @Autowired
    UnitRepository unitRepository;

    public void Handle(ActivateUnitCommand command) throws Exception {
        Unit unit = unitReadRepository.findOne(command.getUnitId());
        if (unit == null)
            throw new CoopException("error.unitNotFound");

        unit.setIsActive(!unit.getIsActive());
        unit.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        unit.setUpdateUserId(command.getUpdateUserId());
        unitRepository.update(unit);
    }
}
