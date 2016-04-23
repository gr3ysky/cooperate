package cooperate.app.business.producer.images.updateProducerImage;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProducerImageCommand implements ICommand {
    @Autowired
    UpdateProducerImageCommandHandler handler;
    private int producerImageId;
    private int producerId;
    private String imageUrl;
    private String altText;
    private int orderNo;
    private int updateUserId;
    private boolean isActive;

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public IHandleCommand<UpdateProducerImageCommand> getHandler() {
        return handler;
    }


}
