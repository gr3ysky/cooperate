package cooperate.app.data.read;

import cooperate.app.entity.Unit;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.unit.UnitFilterDto;
import cooperate.infrastructure.dto.unit.UnitListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnitReadRepository extends GenericReadRepository<Unit> {
    public UnitReadRepository() {
        setClazz(Unit.class);
    }

    public ListResponse<UnitListDto> ListUnits(ListRequest<UnitFilterDto, UnitListDto> request) throws Exception {
        ListResponse<UnitListDto> units = new ListResponse<UnitListDto>();
        List<UnitListDto> list = new ArrayList<UnitListDto>();
        list = exetuteListReader(UnitListDto.class, "p_list_unit_grid",
                request.getFilter().getName(),
                request.getFilter().getAbbrevation(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        units.setData(list);
        units.setDraw(request.getDraw());
        units.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        units.setRecordsTotal((Long) getOutputValue("p_total"));
        return units;
    }
}
