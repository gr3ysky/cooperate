package cooperate.app.business.saleType.addSaleType;


import cooperate.app.data.write.SaleTypeRepository;
import cooperate.app.entity.SaleType;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddSaleTypeCommandHandler implements IHandleCommand<AddSaleTypeCommand> {

    @Autowired
    SaleTypeRepository saleTypeRepository;

    public void Handle(AddSaleTypeCommand command) throws Exception {
        SaleType saleType = new SaleType();
        saleType.setCreateDate(new Timestamp(System.currentTimeMillis()));
        saleType.setCreateUserId(command.getCreateUserId());
        saleType.setIsActive(command.isActive());
        saleType.setName(WordUtils.capitalizeFully(command.getName()));
        saleType.setResourceKey(command.getResourceKey());
        saleTypeRepository.create(saleType);
    }
}
