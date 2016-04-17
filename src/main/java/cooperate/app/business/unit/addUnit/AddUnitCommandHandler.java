package cooperate.app.business.unit.addUnit;

import cooperate.app.data.write.UnitRepository;
import cooperate.app.entity.Unit;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddUnitCommandHandler implements IHandleCommand<AddUnitCommand> {
    @Autowired
    UnitRepository unitRepository;

    public void Handle(AddUnitCommand command) throws Exception {
        Unit unit = new Unit();
        unit.setAbbrevation(command.getAbbrevation());
        unit.setCreateDate(new Timestamp(System.currentTimeMillis()));
        unit.setCreateUserId(command.getCreateUserId());
        unit.setIsActive(command.isActive());
        unit.setName(WordUtils.capitalizeFully(command.getName()));
        unit.setResourceKey(command.getResourceKey());
        unitRepository.create(unit);
    }
}
