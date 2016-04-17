package cooperate.app.business.saleType;

import cooperate.app.ServiceBase;
import cooperate.app.business.saleType.activateSaleType.ActivateSaleTypeCommand;
import cooperate.app.business.saleType.getSaleType.GetSaleTypeRequest;
import cooperate.app.business.saleType.listSaleTypes.ListSaleTypesRequestHandler;
import cooperate.app.business.saleType.updateSaleType.UpdateSaleTypeCommand;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.saleType.SaleTypeDto;
import cooperate.infrastructure.dto.saleType.SaleTypeFilterDto;
import cooperate.infrastructure.dto.saleType.SaleTypeListDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleTypeService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public ListResponse<SaleTypeListDto> ListSaleTypes(ListRequest<SaleTypeFilterDto, SaleTypeListDto> request) throws Exception {
        request.setHandler((ListSaleTypesRequestHandler) context.getBean("listSaleTypesRequestHandler"));
        return mediator.request(request);
    }

    @Transactional
    public void AddSaleType(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateSaleType(ActivateSaleTypeCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public SaleTypeDto GetSaleTypeDto(GetSaleTypeRequest request) throws Exception {

        return mediator.request(request);
    }

    @Transactional
    public void UpdateSaleType(UpdateSaleTypeCommand command) throws Exception {
        mediator.send(command);
    }
}
