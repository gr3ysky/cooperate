package cooperate.infrastructure.dto.producerImage;

public class ProducerImageListDto {
    int producerImageId;
    private int producerId;
    private String imageUrl;
    private String altText;
    private int orderNo;
    private boolean isActive;
    private String producerName;

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public int getProducerImageId() {
        return producerImageId;
    }

    public void setProducerImageId(int producerImageId) {
        this.producerImageId = producerImageId;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}


