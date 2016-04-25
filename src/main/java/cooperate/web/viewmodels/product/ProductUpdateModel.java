package cooperate.web.viewmodels.product;

import javax.validation.constraints.NotNull;

public class ProductUpdateModel extends ProductCreateModel {
    @NotNull
    private Integer productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
