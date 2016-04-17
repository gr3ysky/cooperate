package cooperate.app.business.producer.getProducer;

import cooperate.infrastructure.dto.producer.ProducerDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProducerRequest implements IRequest<GetProducerRequest, ProducerDto> {

    @Autowired
    GetProducerRequestHandler handler;

    private int producerId;

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public IHandleRequest<GetProducerRequest, ProducerDto> getHandler() {
        return handler;
    }
}
