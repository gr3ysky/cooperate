package cooperate.app.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_feature")
public class ProductFeature extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productFeatureId;
    @Length(max = 45, min = 1)
    private String name;
    @Length(max = 45, min = 1)
    private String title;
    @Length(max = 50, min = 1)
    private String nameResourceKey;
    @Length(max = 50, min = 1)
    private String titleResourceKey;
    @Length(max = 200, min = 1)
    private String imageUrl;
    private boolean isActive;
    private java.sql.Timestamp createDate;
    private Integer createUserId;
    private Integer updateUserId;
    private java.sql.Timestamp updateDate;

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameResourceKey() {
        return nameResourceKey;
    }

    public void setNameResourceKey(String nameResourceKey) {
        this.nameResourceKey = nameResourceKey;
    }

    public String getTitleResourceKey() {
        return titleResourceKey;
    }

    public void setTitleResourceKey(String titleResourceKey) {
        this.titleResourceKey = titleResourceKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
