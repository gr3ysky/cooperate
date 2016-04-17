package cooperate.app.business.producer.updateProducer;

import cooperate.app.data.read.ProducerReadRepository;
import cooperate.app.data.write.ProducerRepository;
import cooperate.app.entity.Producer;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateProducerCommandHandler implements IHandleCommand<UpdateProducerCommand> {
    @Autowired
    ProducerReadRepository producerReadRepository;

    @Autowired
    ProducerRepository producerRepository;

    public void Handle(UpdateProducerCommand command) throws Exception {
        Producer producer = producerReadRepository.findOne(command.getProducerId());
        if (producer == null)
            throw new CoopException("error.producerNotFound");
        producer.setIsActive(command.isActive());
        producer.setUpdateUserId(command.getUpdateUserId());
        producer.setName(WordUtils.capitalizeFully(command.getName()));
        producer.setMembersince(new java.sql.Date(command.getMemberSince().getTime()));
        producer.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        producer.setDescription(command.getDescription());
        producer.setLongitude(command.getLongitude());
        producer.setLatitude(command.getLatitude());
        producerRepository.update(producer);
    }
}
