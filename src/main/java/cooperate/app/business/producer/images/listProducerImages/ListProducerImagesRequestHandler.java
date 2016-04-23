package cooperate.app.business.producer.images.listProducerImages;

import cooperate.app.data.read.ProducerImageReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producerImage.ProducerImageFilterDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListProducerImagesRequestHandler implements IHandleRequest<ListRequest<ProducerImageFilterDto, ProducerImageListDto>, ListResponse<ProducerImageListDto>> {
    @Autowired
    private ProducerImageReadRepository producerImageReadRepository;

    public ListResponse<ProducerImageListDto> Handle(ListRequest<ProducerImageFilterDto, ProducerImageListDto> request) throws Exception {
        return producerImageReadRepository.ListProducerImages(request);
    }
}
