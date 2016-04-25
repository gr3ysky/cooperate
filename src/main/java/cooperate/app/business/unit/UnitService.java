package cooperate.app.business.unit;

import cooperate.app.ServiceBase;
import cooperate.app.business.unit.activateUnit.ActivateUnitCommand;
import cooperate.app.business.unit.addUnit.AddUnitCommand;
import cooperate.app.business.unit.getUnit.GetUnitRequest;
import cooperate.app.business.unit.listUnits.ListUnitsRequestHandler;
import cooperate.app.data.read.UnitReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.unit.UnitDto;
import cooperate.infrastructure.dto.unit.UnitFilterDto;
import cooperate.infrastructure.dto.unit.UnitListDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Autowired
    UnitReadRepository unitReadRepository;

    @Transactional(readOnly = true)
    public List<SelectListItem> getUnitsDropDown() throws Exception {
        return unitReadRepository.getUnitsDropDown();
    }

    @Transactional(readOnly = true)
    public ListResponse<UnitListDto> ListUnits(ListRequest<UnitFilterDto, UnitListDto> request) throws Exception {
        request.setHandler((ListUnitsRequestHandler) context.getBean("listUnitsRequestHandler"));
        return mediator.request(request);
    }

    @Transactional
    public void AddUnit(AddUnitCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateUnit(ActivateUnitCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public UnitDto GetUnitDto(GetUnitRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void UpdateUnit(ICommand command) throws Exception {
        mediator.send(command);
    }
}
