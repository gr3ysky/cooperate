package cooperate.app.business.unit.updateUnit;

import cooperate.app.data.read.UnitReadRepository;
import cooperate.app.data.write.UnitRepository;
import cooperate.app.entity.Unit;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUnitCommandHandler implements IHandleCommand<UpdateUnitCommand> {
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    UnitReadRepository unitReadRepository;

    public void Handle(UpdateUnitCommand command) throws Exception {
        Unit unit = unitReadRepository.findOne(command.getUnitId());
        if (unit == null)
            throw new CoopException("error.unitNotFound");
        unit.setIsActive(command.getIsActive());
        unit.setUpdateUserId(command.getUpdateUserId());
        unit.setName(WordUtils.capitalizeFully(command.getName()));
        unit.setIsActive(command.getIsActive());
        unit.setResourceKey(command.getResourceKey());
        unit.setAbbrevation(command.getAbbrevation());
        unitRepository.update(unit);
    }
}
