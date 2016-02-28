package cooperate.app.data.read;

import cooperate.infrastructure.dto.RolePermissionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionReadRepository extends Database {

    public List<RolePermissionDto> getRolePermissionList() throws Exception {
        return exetuteListReader(RolePermissionDto.class, "p_list_role_permissions");
    }

}


