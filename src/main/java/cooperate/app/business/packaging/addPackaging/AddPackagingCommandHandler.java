package cooperate.app.business.packaging.addPackaging;

import cooperate.app.data.write.PackagingRepository;
import cooperate.app.entity.Packaging;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddPackagingCommandHandler implements IHandleCommand<AddPackagingCommand> {

    @Autowired
    PackagingRepository packagingRepository;

    public void Handle(AddPackagingCommand command) throws Exception {
        Packaging packaging = new Packaging();
        packaging.setCreateDate(new Timestamp(System.currentTimeMillis()));
        packaging.setCreateUserId(command.getCreateUserId());
        packaging.setIsActive(command.isActive());
        packaging.setName(WordUtils.capitalizeFully(command.getName()));
        packaging.setResourceKey(command.getResourceKey());
        packagingRepository.create(packaging);
    }
}
