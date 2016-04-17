package cooperate.app.business.producer.getProducer;

import cooperate.app.data.read.ProducerReadRepository;
import cooperate.app.entity.Producer;
import cooperate.infrastructure.dto.producer.ProducerDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProducerRequestHandler implements IHandleRequest<GetProducerRequest, ProducerDto> {
    @Autowired
    ProducerReadRepository producerReadRepository;

    public ProducerDto Handle(GetProducerRequest request) throws Exception {
        Producer pc = producerReadRepository.findOne(request.getProducerId());
        if (pc == null)
            throw new CoopException("error.producerNotFound");
        ProducerDto dto = new ProducerDto();
        dto.setProducerId(pc.getProducerId());
        dto.setIsActive(pc.getIsActive());
        dto.setName(pc.getName());
        dto.setDescription(pc.getDescription());
        dto.setLongitude(pc.getLongitude());
        dto.setLatitude(pc.getLatitude());
        dto.setMemberSince(pc.getMembersince());
        return dto;
    }
}
