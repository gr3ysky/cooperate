package cooperate.app.business.producer;

import cooperate.app.ServiceBase;
import cooperate.app.business.producer.activateProducer.ActivateProducerCommand;
import cooperate.app.business.producer.addProducer.AddProducerCommand;
import cooperate.app.business.producer.getProducer.GetProducerRequest;
import cooperate.app.business.producer.listProducers.ListProducersRequestHandler;
import cooperate.app.business.producer.updateProducer.UpdateProducerCommand;
import cooperate.app.data.read.ProducerReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.producer.ProducerDto;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProducerService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Autowired
    ProducerReadRepository producerReadRepository;

    @Transactional(readOnly = true)
    public List<SelectListItem> getProducersDropDown() throws Exception {
        return producerReadRepository.getProducersDropDown();
    }


    @Transactional(readOnly = true)
    public ListResponse<ProducerListDto> ListProducers(ListRequest<ProducerFilterDto, ProducerListDto> request) throws Exception {
        request.setHandler((ListProducersRequestHandler) context.getBean("listProducersRequestHandler"));
        return mediator.request(request);
    }

    @Transactional(readOnly = true)
    public ProducerDto GetProducerDto(GetProducerRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void AddProducer(AddProducerCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProducer(ActivateProducerCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void UpdateProducer(UpdateProducerCommand command) throws Exception {
        mediator.send(command);
    }
}
