package cooperate.app.data.write;

import cooperate.app.entity.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCategoryRepository extends GenericRepository<ProductCategory> {
    public ProductCategoryRepository() {
        setClazz(ProductCategory.class);
    }

}
