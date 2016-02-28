package cooperate.app.business.permission;

import cooperate.app.business.permission.addPermission.AddPermissionCommand;
import cooperate.app.data.read.PermissionReadRepository;
import cooperate.infrastructure.dto.RolePermissionDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private Mediator mediator;
    @Autowired
    private PermissionReadRepository permissionReadRepository;

    @Transactional
    public void addPermission(AddPermissionCommand command) {
        mediator.send(command);
    }

    @Transactional
    @Cacheable(value = "rolePermissionDtos")
    public List<RolePermissionDto> getRolePermissionList() throws Exception {
        return permissionReadRepository.getRolePermissionList();
    }
}
