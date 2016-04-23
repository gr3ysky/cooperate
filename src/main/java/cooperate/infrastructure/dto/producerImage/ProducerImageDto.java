package cooperate.infrastructure.dto.producerImage;

import cooperate.infrastructure.mediation.IResponse;

import java.sql.Timestamp;

public class ProducerImageDto implements IResponse {
    int producerImageId;
    private int producerId;
    private String imageUrl;
    private String altText;
    private int orderNo;
    private boolean isActive;
    private java.sql.Timestamp createDate;
    private Integer createUserId;
    private Integer updateUserId;
    private java.sql.Timestamp updateDate;

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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
