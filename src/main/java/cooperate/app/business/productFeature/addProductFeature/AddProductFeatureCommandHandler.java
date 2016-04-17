package cooperate.app.business.productFeature.addProductFeature;

import cooperate.app.data.write.ProductFeatureRepository;
import cooperate.app.entity.ProductFeature;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProductFeatureCommandHandler implements IHandleCommand<AddProductFeatureCommand> {

    @Autowired
    ProductFeatureRepository productFeatureRepository;

    public void Handle(AddProductFeatureCommand command) throws Exception {
        ProductFeature productFeature = new ProductFeature();
        productFeature.setCreateDate(new Timestamp(System.currentTimeMillis()));
        productFeature.setCreateUserId(command.getCreateUserId());
        productFeature.setIsActive(command.isActive());
        productFeature.setName(WordUtils.capitalizeFully(command.getName()));
        productFeature.setTitle(WordUtils.capitalizeFully(command.getTitle()));
        productFeature.setNameResourceKey(command.getNameResourceKey());
        productFeature.setTitleResourceKey(command.getTitleResourceKey());
        productFeature.setImageUrl(command.getImageUrl());
        productFeatureRepository.create(productFeature);
    }
}
