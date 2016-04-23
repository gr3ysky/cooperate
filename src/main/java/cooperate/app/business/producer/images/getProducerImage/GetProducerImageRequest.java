package cooperate.app.business.producer.images.getProducerImage;

import cooperate.infrastructure.dto.producerImage.ProducerImageDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProducerImageRequest implements IRequest<GetProducerImageRequest, ProducerImageDto> {

    @Autowired
    GetProducerImageRequestHandler handler;

    private int producerId;
    private int producerImageId;

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public int getProducerImageId() {
        return producerImageId;
    }

    public void setProducerImageId(int producerImageId) {
        this.producerImageId = producerImageId;
    }

    public IHandleRequest<GetProducerImageRequest, ProducerImageDto> getHandler() {
        return handler;
    }
}
