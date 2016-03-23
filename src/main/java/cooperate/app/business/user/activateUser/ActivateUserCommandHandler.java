package cooperate.app.business.user.activateUser;

import cooperate.app.data.read.UserReadRepository;
import cooperate.app.data.write.UserRepository;
import cooperate.app.entity.User;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivateUserCommandHandler implements IHandleCommand<ActivateUserCommand> {

    @Autowired
    UserReadRepository userReadRepository;

    @Autowired
    UserRepository userRepository;

    public void Handle(ActivateUserCommand command) throws CoopException {
        User user = userReadRepository.findOne(command.getUserId());
        if (user == null) {
            throw new CoopException("error.userNotFound");
        }

        user.setIsActive(!user.getIsActive());
        user.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setUpdateUserId(command.getUpdateUserId());
        userRepository.update(user);
    }
}
