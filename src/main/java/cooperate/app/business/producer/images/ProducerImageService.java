package cooperate.app.business.producer.images;

import cooperate.app.ServiceBase;
import cooperate.app.business.producer.images.activateProducerImage.ActivateProducerImageCommand;
import cooperate.app.business.producer.images.addProducerImage.AddProducerImageCommand;
import cooperate.app.business.producer.images.getProducerImage.GetProducerImageRequest;
import cooperate.app.business.producer.images.listProducerImages.ListProducerImagesRequestHandler;
import cooperate.app.business.producer.images.updateProducerImage.UpdateProducerImageCommand;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producerImage.ProducerImageDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageFilterDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageListDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProducerImageService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public ListResponse<ProducerImageListDto> ListProducerImages(ListRequest<ProducerImageFilterDto, ProducerImageListDto> request) throws Exception {
        request.setHandler((ListProducerImagesRequestHandler) context.getBean("listProducerImagesRequestHandler"));
        return mediator.request(request);

    }

    @Transactional(readOnly = true)
    public ProducerImageDto GetProducerImageDto(GetProducerImageRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void AddProducerImage(AddProducerImageCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProducerImage(ActivateProducerImageCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void UpdateProducerImage(UpdateProducerImageCommand command) throws Exception {
        mediator.send(command);
    }
}
