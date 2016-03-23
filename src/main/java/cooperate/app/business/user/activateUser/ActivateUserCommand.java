package cooperate.app.business.user.activateUser;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateUserCommand implements ICommand {
    @Autowired
    private ActivateUserCommandHandler handler;
    private int userId;
    private int updateUserId;

    public IHandleCommand<ActivateUserCommand> getHandler() {
        return handler;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }
}
