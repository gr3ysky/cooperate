package cooperate.app.business.unit.listUnits;


import cooperate.app.data.read.UnitReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.unit.UnitFilterDto;
import cooperate.infrastructure.dto.unit.UnitListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListUnitsRequestHandler implements IHandleRequest<ListRequest<UnitFilterDto, UnitListDto>, ListResponse<UnitListDto>> {
    @Autowired
    UnitReadRepository unitReadRepository;

    public ListResponse<UnitListDto> Handle(ListRequest<UnitFilterDto, UnitListDto> request) throws Exception {
        return unitReadRepository.ListUnits(request);
    }
}
