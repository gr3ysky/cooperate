package cooperate.app.business.role;

import cooperate.app.ServiceBase;
import cooperate.app.data.read.RoleReadRepository;
import cooperate.infrastructure.dto.SelectListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService extends ServiceBase {
    @Autowired
    RoleReadRepository roleReadRepository;

    @Transactional
    public List<SelectListItem> getRolesList() throws Exception {
        return roleReadRepository.getRoles();
    }
}
