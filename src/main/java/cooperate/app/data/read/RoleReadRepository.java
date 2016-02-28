package cooperate.app.data.read;

import cooperate.infrastructure.dto.SelectListItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleReadRepository extends Database {
    public List<SelectListItem> getRoles() throws Exception {
        return exetuteListReader(SelectListItem.class, "p_list_roles_for_combo");
    }
}
