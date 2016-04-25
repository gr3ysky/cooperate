package cooperate.web.viewmodels.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductCreateModel {
    @Length(max = 45, min = 5)
    private String name;
    @Min(1)
    @NotNull
    private Integer producerId;
    @Min(1)
    @NotNull
    private Integer productCategoryId;
    @Min(1)
    @NotNull
    private Integer saleTypeId;
    @Min(1)
    @NotNull
    private Integer unitId;
    @NotNull
    @Min(1)
    private Integer packagingId;
    @NotNull
    @Min(1)
    private Double unitPrice;
    @NotNull
    @Min(1)
    private Integer stockCount;
    @Length(max = 200)
    private String tags;
    private boolean isPrePayed;
    private boolean isActive;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Integer getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isPrePayed() {
        return isPrePayed;
    }

    public void setPrePayed(boolean prePayed) {
        isPrePayed = prePayed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
