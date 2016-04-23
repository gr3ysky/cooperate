package cooperate.app.business.producer.images.getProducerImage;

import cooperate.app.data.read.ProducerImageReadRepository;
import cooperate.app.entity.ProducerImage;
import cooperate.infrastructure.dto.producerImage.ProducerImageDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProducerImageRequestHandler implements IHandleRequest<GetProducerImageRequest, ProducerImageDto> {
    @Autowired
    ProducerImageReadRepository producerImageReadRepository;

    public ProducerImageDto Handle(GetProducerImageRequest request) throws Exception {
        ProducerImage producerImage = producerImageReadRepository.findOne(request.getProducerImageId());
        if (producerImage == null || producerImage.getProducerId() != request.getProducerId())
            throw new CoopException("error.producerImageNotFound");
        ProducerImageDto dto = new ProducerImageDto();
        dto.setProducerId(producerImage.getProducerId());
        dto.setIsActive(producerImage.getIsActive());
        dto.setImageUrl(producerImage.getImageUrl());
        dto.setOrderNo(producerImage.getOrderNo());
        dto.setAltText(producerImage.getAltText());
        dto.setProducerImageId(producerImage.getProducerImageId());
        return dto;
    }
}
