package cooperate.infrastructure.dto.productFeature;


import cooperate.infrastructure.mediation.IResponse;

import java.sql.Timestamp;

public class ProductFeatureDto implements IResponse {
    private int productFeatureId;
    private String name;
    private String nameResourceKey;
    private String title;
    private String titleResourceKey;
    private String imageUrl;
    private boolean isActive;
    private Timestamp createDate;
    private Integer createUserId;
    private Integer updateUserId;
    private Timestamp UpdateDate;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return UpdateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        UpdateDate = updateDate;
    }

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
    }

    public String getNameResourceKey() {
        return nameResourceKey;
    }

    public void setNameResourceKey(String nameResourceKey) {
        this.nameResourceKey = nameResourceKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleResourceKey() {
        return titleResourceKey;
    }

    public void setTitleResourceKey(String titleResourceKey) {
        this.titleResourceKey = titleResourceKey;
    }
}
