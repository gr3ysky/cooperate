package cooperate.app.data.write;

import cooperate.app.entity.Unit;
import org.springframework.stereotype.Repository;

@Repository
public class UnitRepository extends GenericRepository<Unit> {
    public UnitRepository() {
        setClazz(Unit.class);
    }
}
