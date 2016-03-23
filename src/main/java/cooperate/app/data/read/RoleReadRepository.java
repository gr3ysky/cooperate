package cooperate.app.data.read;

import cooperate.app.entity.Role;
import cooperate.infrastructure.dto.SelectListItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleReadRepository extends GenericReadRepository<Role> {

    public RoleReadRepository() {
        setClazz(Role.class);
    }
    
    public List<SelectListItem> getRoles() throws Exception {
        return exetuteListReader(SelectListItem.class, "p_list_roles_for_combo");
    }
}
