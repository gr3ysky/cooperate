package cooperate.app.business.saleType.getSaleType;


import cooperate.app.data.read.SaleTypeReadRepository;
import cooperate.app.entity.SaleType;
import cooperate.infrastructure.dto.saleType.SaleTypeDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSaleTypeRequestHandler implements IHandleRequest<GetSaleTypeRequest, SaleTypeDto> {
    @Autowired
    SaleTypeReadRepository saleTypeReadRepository;

    public SaleTypeDto Handle(GetSaleTypeRequest request) throws Exception {
        SaleType saleType = saleTypeReadRepository.findOne(request.getSaleTypeId());
        if (saleType == null)
            throw new CoopException("error.saleTypeNotFound");
        SaleTypeDto dto = new SaleTypeDto();
        dto.setSaleTypeId(saleType.getSaleTypeId());
        dto.setIsActive(saleType.getIsActive());
        dto.setName(saleType.getName());
        dto.setResourceKey(saleType.getResourceKey());
        return dto;
    }
}
