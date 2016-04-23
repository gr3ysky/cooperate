package cooperate.app.business.producer.images.addProducerImage;

import cooperate.app.data.write.ProducerImageRepository;
import cooperate.app.entity.ProducerImage;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProducerImageCommandHandler implements IHandleCommand<AddProducerImageCommand> {
    @Autowired
    ProducerImageRepository producerImageRepository;

    public void Handle(AddProducerImageCommand command) throws Exception {
        ProducerImage producerImage = new ProducerImage();
        producerImage.setCreateDate(new Timestamp(System.currentTimeMillis()));
        producerImage.setCreateUserId(command.getCreateUserId());
        producerImage.setIsActive(command.isActive());
        producerImage.setProducerId(command.getProducerId());
        producerImage.setOrderNo(command.getOrderNo());
        producerImage.setAltText(command.getAltText());
        producerImage.setImageUrl(command.getImageUrl());
        producerImageRepository.create(producerImage);
    }
}
