package cooperate.app.business.saleType.activateSaleType;

import cooperate.app.data.read.SaleTypeReadRepository;
import cooperate.app.data.write.SaleTypeRepository;
import cooperate.app.entity.SaleType;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateSaleTypeCommandHandler implements IHandleCommand<ActivateSaleTypeCommand> {
    @Autowired
    SaleTypeReadRepository saleTypeReadRepository;
    @Autowired
    SaleTypeRepository saleTypeRepository;

    public void Handle(ActivateSaleTypeCommand command) throws Exception {

        SaleType saleType = saleTypeReadRepository.findOne(command.getSaleTypeId());
        if (saleType == null)
            throw new CoopException("error.saleTypeNotFound");

        saleType.setIsActive(!saleType.getIsActive());
        saleType.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        saleType.setUpdateUserId(command.getUpdateUserId());
        saleTypeRepository.update(saleType);
    }
}
