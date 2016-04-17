package cooperate.infrastructure.dto.saleType;


import cooperate.infrastructure.mediation.IResponse;

import java.sql.Timestamp;

public class SaleTypeDto implements IResponse {
    private int saleTypeId;
    private String name;
    private String resourceKey;
    private boolean isActive;
    private java.sql.Timestamp createDate;
    private Integer createUserId;
    private Integer updateUserId;
    private java.sql.Timestamp UpdateDate;

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
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
}
