package cooperate.app.business.saleType.listSaleTypes;

import cooperate.app.data.read.SaleTypeReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.saleType.SaleTypeFilterDto;
import cooperate.infrastructure.dto.saleType.SaleTypeListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListSaleTypesRequestHandler implements IHandleRequest<ListRequest<SaleTypeFilterDto, SaleTypeListDto>, ListResponse<SaleTypeListDto>> {
    @Autowired
    SaleTypeReadRepository saleTypeReadRepository;

    public ListResponse<SaleTypeListDto> Handle(ListRequest<SaleTypeFilterDto, SaleTypeListDto> request) throws Exception {
        return saleTypeReadRepository.ListSaleTypes(request);
    }
}
