package cooperate.app.data.write;

import cooperate.app.entity.SaleType;
import org.springframework.stereotype.Repository;

@Repository
public class SaleTypeRepository extends GenericRepository<SaleType> {
    public SaleTypeRepository() {
        setClazz(SaleType.class);
    }
}
