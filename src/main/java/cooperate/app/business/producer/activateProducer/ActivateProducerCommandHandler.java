package cooperate.app.business.producer.activateProducer;

import cooperate.app.data.read.ProducerReadRepository;
import cooperate.app.data.write.ProducerRepository;
import cooperate.app.entity.Producer;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivateProducerCommandHandler implements IHandleCommand<ActivateProducerCommand> {

    @Autowired
    ProducerRepository producerRepository;
    @Autowired
    ProducerReadRepository producerReadRepository;

    public void Handle(ActivateProducerCommand command) throws Exception {

        Producer pc = producerReadRepository.findOne(command.getProducerId());
        if (pc == null)
            throw new CoopException("error.producerNotFound");

        pc.setIsActive(!pc.getIsActive());
        pc.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        pc.setUpdateUserId(command.getUpdateUserId());
        producerRepository.update(pc);
    }
}
