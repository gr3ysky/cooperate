package cooperate.app.data.read;

import cooperate.app.entity.Permission;
import cooperate.infrastructure.dto.RolePermissionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionReadRepository extends GenericReadRepository<Permission> {

    public PermissionReadRepository() {
        setClazz(Permission.class);
    }

    public List<RolePermissionDto> getRolePermissionList() throws Exception {
        return exetuteListReader(RolePermissionDto.class, "p_list_role_permissions");
    }

}


