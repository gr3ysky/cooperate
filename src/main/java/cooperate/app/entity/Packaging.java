package cooperate.app.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "packaging")
public class Packaging extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packagingId;
    @Length(max = 45, min = 1)
    private String name;
    @Length(max = 50, min = 1)
    private String resourceKey;
    private boolean isActive;
    private java.sql.Timestamp createDate;
    private Integer createUserId;
    private Integer updateUserId;
    private java.sql.Timestamp UpdateDate;

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
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
