package cooperate.app.business.packaging.activatePackaging;

import cooperate.app.data.read.PackagingReadRepository;
import cooperate.app.data.write.PackagingRepository;
import cooperate.app.entity.Packaging;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivatePackagingCommandHandler implements IHandleCommand<ActivatePackagingCommand> {

    @Autowired
    PackagingReadRepository packagingReadRepository;
    @Autowired
    PackagingRepository packagingRepository;

    public void Handle(ActivatePackagingCommand command) throws Exception {

        Packaging packaging = packagingReadRepository.findOne(command.getPackagingId());
        if (packaging == null)
            throw new CoopException("error.productCategoryNotFound");

        packaging.setIsActive(!packaging.getIsActive());
        packaging.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        packaging.setUpdateUserId(command.getUpdateUserId());
        packagingRepository.update(packaging);
    }
}
