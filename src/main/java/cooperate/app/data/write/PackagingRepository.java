package cooperate.app.data.write;

import cooperate.app.entity.Packaging;
import org.springframework.stereotype.Repository;

@Repository
public class PackagingRepository extends GenericRepository<Packaging> {
    public PackagingRepository() {
        setClazz(Packaging.class);
    }
}
