package cooperate.app.business.permission.addPermission;

import cooperate.app.data.write.PermissionRepository;
import cooperate.app.entity.Permission;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPermissionCommandHandler implements IHandleCommand<AddPermissionCommand> {
    @Autowired
    private PermissionRepository permissionRepository;

    public void Handle(AddPermissionCommand command) {
        Permission permission = new Permission();
        permission.IsActive = true;
        permission.PermissionCode = command.PermissionCode;
        permission.PermissionName = command.PermissionName;
        permission.CreateUserId = command.OperatingUserId;
        permission.CreateDate = new java.sql.Timestamp(System.currentTimeMillis());
        permissionRepository.create(permission);
    }
}
