package cooperate.app.business.producer.images.activateProducerImage;

import cooperate.app.data.read.ProducerImageReadRepository;
import cooperate.app.data.write.ProducerImageRepository;
import cooperate.app.entity.ProducerImage;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProducerImageCommandHandler implements IHandleCommand<ActivateProducerImageCommand> {

    @Autowired
    ProducerImageRepository producerImageRepository;
    @Autowired
    ProducerImageReadRepository producerImageReadRepository;

    public void Handle(ActivateProducerImageCommand command) throws Exception {

        ProducerImage producerImage = producerImageReadRepository.findOne(command.getProducerImageId());
        if (producerImage == null)
            throw new CoopException("error.producerImageNotFound");

        producerImage.setIsActive(!producerImage.getIsActive());
        producerImage.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        producerImage.setUpdateUserId(command.getUpdateUserId());
        producerImageRepository.update(producerImage);
    }
}
