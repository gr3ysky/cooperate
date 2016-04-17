package cooperate.app.data.write;

import cooperate.app.entity.ProductFeature;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFeatureRepository extends GenericRepository<ProductFeature> {
    public ProductFeatureRepository() {
        setClazz(ProductFeature.class);
    }
}
