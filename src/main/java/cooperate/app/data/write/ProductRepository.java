package cooperate.app.data.write;

import cooperate.app.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends GenericRepository<Product> {
    public ProductRepository() {
        setClazz(Product.class);
    }
}
