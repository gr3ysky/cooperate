package cooperate.infrastructure.dto.productFeature;


public class ProductFeatureListDto {
    private int productFeatureId;
    private String name;
    private String title;
    private String nameResourceKey;
    private String titleResourceKey;
    private String imageUrl;
    private boolean isActive;

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

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
