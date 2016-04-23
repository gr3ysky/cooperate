package cooperate.app.business.producer.images.updateProducerImage;

import cooperate.app.data.read.ProducerImageReadRepository;
import cooperate.app.data.write.ProducerImageRepository;
import cooperate.app.entity.ProducerImage;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateProducerImageCommandHandler implements IHandleCommand<UpdateProducerImageCommand> {
    @Autowired
    ProducerImageReadRepository producerImageReadRepository;

    @Autowired
    ProducerImageRepository producerImageRepository;

    public void Handle(UpdateProducerImageCommand command) throws Exception {
        ProducerImage producerImage = producerImageReadRepository.findOne(command.getProducerImageId());
        if (producerImage == null || producerImage.getProducerId() != command.getProducerId())
            throw new CoopException("error.producerImageNotFound");
        producerImage.setCreateDate(new Timestamp(System.currentTimeMillis()));
        producerImage.setCreateUserId(command.getUpdateUserId());
        producerImage.setIsActive(command.isActive());
        // producerImage.setProducerId(command.getProducerId());
        producerImage.setOrderNo(command.getOrderNo());
        producerImage.setAltText(command.getAltText());
        if (command.getImageUrl() != null)
            producerImage.setImageUrl(command.getImageUrl());
        producerImageRepository.update(producerImage);
    }
}
