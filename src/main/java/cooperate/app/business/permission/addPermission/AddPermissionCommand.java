package cooperate.app.business.permission.addPermission;

import cooperate.infrastructure.mediation.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPermissionCommand implements ICommand {
    public String PermissionCode;
    public String PermissionName;
    public long OperatingUserId;
    @Autowired
    AddPermissionCommandHandler handler;

    public AddPermissionCommandHandler getHandler() {
        return handler;
    }
}
