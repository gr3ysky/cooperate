package cooperate.app.data.write;

import cooperate.app.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionRepository extends GenericRepository<Permission> {
    public void PermissionRepository() {
        setClazz(Permission.class);
    }
}
