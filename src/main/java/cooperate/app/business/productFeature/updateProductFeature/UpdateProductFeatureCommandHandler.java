package cooperate.app.business.productFeature.updateProductFeature;

import cooperate.app.data.read.ProductFeatureReadRepository;
import cooperate.app.data.write.ProductFeatureRepository;
import cooperate.app.entity.ProductFeature;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateProductFeatureCommandHandler implements IHandleCommand<UpdateProductFeatureCommand> {

    @Autowired
    ProductFeatureReadRepository productFeatureReadRepository;
    @Autowired
    ProductFeatureRepository productFeatureRepository;

    public void Handle(UpdateProductFeatureCommand command) throws Exception {

        ProductFeature productFeature = productFeatureReadRepository.findOne(command.getProductFeatureId());
        if (productFeature == null)
            throw new CoopException("error.productFeatureNotFound");

        productFeature.setCreateDate(new Timestamp(System.currentTimeMillis()));
        productFeature.setCreateUserId(command.getUpdateUserId());
        productFeature.setIsActive(command.getIsActive());
        productFeature.setName(WordUtils.capitalizeFully(command.getName()));
        productFeature.setTitle(WordUtils.capitalizeFully(command.getTitle()));
        productFeature.setNameResourceKey(command.getNameResourceKey());
        productFeature.setTitleResourceKey(command.getTitleResourceKey());
        productFeature.setImageUrl(command.getImageUrl());
        productFeatureRepository.create(productFeature);

    }
}
