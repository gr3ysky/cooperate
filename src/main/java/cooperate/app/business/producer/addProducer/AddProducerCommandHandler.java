package cooperate.app.business.producer.addProducer;

import cooperate.app.data.write.ProducerRepository;
import cooperate.app.entity.Producer;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AddProducerCommandHandler implements IHandleCommand<AddProducerCommand> {
    @Autowired
    ProducerRepository producerRepository;

    public void Handle(AddProducerCommand command) throws Exception {
        Producer producer = new Producer();
        producer.setCreateDate(new Timestamp(System.currentTimeMillis()));
        producer.setCreateUserId(command.getCreateUserId());
        producer.setIsActive(command.isActive());
        producer.setName(WordUtils.capitalizeFully(command.getName()));
        producer.setDescription(command.getDescription());
        if (command.getMemberSince() != null)
            producer.setMembersince(new java.sql.Date(command.getMemberSince().getTime()));
        producerRepository.create(producer);
    }
}
