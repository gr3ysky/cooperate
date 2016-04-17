package cooperate.app.business.saleType.updateSaleType;

import cooperate.app.data.read.SaleTypeReadRepository;
import cooperate.app.data.write.SaleTypeRepository;
import cooperate.app.entity.SaleType;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSaleTypeCommandHandler implements IHandleCommand<UpdateSaleTypeCommand> {
    @Autowired
    SaleTypeReadRepository saleTypeReadRepository;
    @Autowired
    SaleTypeRepository saleTypeRepository;

    public void Handle(UpdateSaleTypeCommand command) throws Exception {
        SaleType saleType = saleTypeReadRepository.findOne(command.getSaleTypeId());
        if (saleType == null)
            throw new CoopException("error.saleTypeNotFound");
        saleType.setIsActive(command.getIsActive());
        saleType.setUpdateUserId(command.getUpdateUserId());
        saleType.setName(WordUtils.capitalizeFully(command.getName()));
        saleType.setIsActive(command.getIsActive());
        saleType.setResourceKey(command.getResourceKey());
        saleTypeRepository.update(saleType);
    }
}
