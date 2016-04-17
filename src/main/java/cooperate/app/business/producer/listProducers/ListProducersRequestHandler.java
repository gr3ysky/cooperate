package cooperate.app.business.producer.listProducers;

import cooperate.app.data.read.ProducerReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProducersRequestHandler implements IHandleRequest<ListRequest<ProducerFilterDto, ProducerListDto>, ListResponse<ProducerListDto>> {
    @Autowired
    private ProducerReadRepository producerReadRepository;

    public ListResponse<ProducerListDto> Handle(ListRequest<ProducerFilterDto, ProducerListDto> request) throws Exception {
        return producerReadRepository.ListProducers(request);
    }
}
