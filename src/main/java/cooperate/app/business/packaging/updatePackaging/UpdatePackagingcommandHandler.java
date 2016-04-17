package cooperate.app.business.packaging.updatePackaging;

import cooperate.app.data.read.PackagingReadRepository;
import cooperate.app.data.write.PackagingRepository;
import cooperate.app.entity.Packaging;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePackagingCommandHandler implements IHandleCommand<UpdatePackagingCommand> {

    @Autowired
    PackagingReadRepository packagingReadRepository;
    @Autowired
    PackagingRepository packagingRepository;

    public void Handle(UpdatePackagingCommand command) throws Exception {

        Packaging packaging = packagingReadRepository.findOne(command.getPackagingId());
        if (packaging == null)
            throw new CoopException("error.packagingNotFound");
        packaging.setIsActive(command.getIsActive());
        packaging.setUpdateUserId(command.getUpdateUserId());
        packaging.setName(WordUtils.capitalizeFully(command.getName()));
        packaging.setIsActive(command.getIsActive());
        packaging.setResourceKey(command.getResourceKey());
        packagingRepository.update(packaging);

    }
}
