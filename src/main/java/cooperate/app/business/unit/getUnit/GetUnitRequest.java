package cooperate.app.business.unit.getUnit;

import cooperate.infrastructure.dto.unit.UnitDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUnitRequest implements IRequest<GetUnitRequest, UnitDto> {
    @Autowired
    GetUnitRequestHandler handler;
    private int unitId;

    public IHandleRequest<GetUnitRequest, UnitDto> getHandler() {
        return handler;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
