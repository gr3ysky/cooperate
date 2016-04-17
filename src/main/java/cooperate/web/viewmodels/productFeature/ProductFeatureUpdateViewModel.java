package cooperate.web.viewmodels.productFeature;

public class ProductFeatureUpdateViewModel extends ProductFatureCreateViewModel {
    private int productFeatureId;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
    }
}
