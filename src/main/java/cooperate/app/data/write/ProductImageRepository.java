package cooperate.app.data.write;

import cooperate.app.entity.ProductImage;
import org.springframework.stereotype.Repository;

@Repository
public class ProductImageRepository extends GenericRepository<ProductImage> {
    public ProductImageRepository() {
        setClazz(ProductImage.class);
    }
}
