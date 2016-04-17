package cooperate.app.business.productFeature.activateProductFeature;

import cooperate.app.data.read.ProductFeatureReadRepository;
import cooperate.app.data.write.ProductFeatureRepository;
import cooperate.app.entity.ProductFeature;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProductFeatureCommandHandler implements IHandleCommand<ActivateProductFeatureCommand> {

    @Autowired
    ProductFeatureReadRepository productFeatureReadRepository;
    @Autowired
    ProductFeatureRepository productFeatureRepository;

    public void Handle(ActivateProductFeatureCommand command) throws Exception {

        ProductFeature productFeature = productFeatureReadRepository.findOne(command.getProductFeatureId());
        if (productFeature == null)
            throw new CoopException("error.productFeatureNotFound");

        productFeature.setIsActive(!productFeature.getIsActive());
        productFeature.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        productFeature.setUpdateUserId(command.getUpdateUserId());
        productFeatureRepository.update(productFeature);
    }
}
