package cooperate.app.business.saleType.getSaleType;

import cooperate.infrastructure.dto.saleType.SaleTypeDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSaleTypeRequest implements IRequest<GetSaleTypeRequest, SaleTypeDto> {
    @Autowired
    GetSaleTypeRequestHandler handler;
    private int saleTypeId;

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public IHandleRequest<GetSaleTypeRequest, SaleTypeDto> getHandler() {
        return handler;
    }
}
