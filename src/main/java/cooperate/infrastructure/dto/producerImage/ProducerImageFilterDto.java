package cooperate.infrastructure.dto.producerImage;

public class ProducerImageFilterDto {
    private int producerId;
    private int isActive;

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getProducerId() {
        return producerId;
    }


    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }
}
