package cooperate.app.business.unit.getUnit;

import cooperate.app.data.read.UnitReadRepository;
import cooperate.app.entity.Unit;
import cooperate.infrastructure.dto.unit.UnitDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUnitRequestHandler implements IHandleRequest<GetUnitRequest, UnitDto> {

    @Autowired
    UnitReadRepository unitReadRepository;

    public UnitDto Handle(GetUnitRequest request) throws Exception {
        Unit unit = unitReadRepository.findOne(request.getUnitId());
        if (unit == null)
            throw new CoopException("error.unitNotFound");
        UnitDto dto = new UnitDto();
        dto.setUnitId(unit.getUnitId());
        dto.setIsActive(unit.getIsActive());
        dto.setName(unit.getName());
        dto.setResourceKey(unit.getResourceKey());
        dto.setAbbrevation(unit.getAbbrevation());
        return dto;
    }
}
