package cooperate.app.business.producer.images.activateProducerImage;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateProducerImageCommand implements ICommand {

    @Autowired
    ActivateProducerImageCommandHandler handler;
    private int producerImageId;
    private int updateUserId;

    public int getProducerImageId() {
        return producerImageId;
    }

    public void setProducerImageId(int producerImageId) {
        this.producerImageId = producerImageId;
    }

    public IHandleCommand<ActivateProducerImageCommand> getHandler() {
        return handler;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

}
