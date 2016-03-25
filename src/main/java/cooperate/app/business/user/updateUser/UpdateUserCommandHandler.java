package cooperate.app.business.user.updateUser;

import cooperate.app.data.read.UserReadRepository;
import cooperate.app.data.write.UserRepository;
import cooperate.app.entity.User;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UpdateUserCommandHandler implements IHandleCommand<UpdateUserCommand> {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReadRepository userReadRepository;

    public void Handle(UpdateUserCommand command) throws Exception {
        User user = userReadRepository.findOne(command.getUserId());
        if (user == null)
            throw new CoopException("error.userNotFound");
        user.setEmail(command.getEmail());
        user.setFullName(command.getFullName());
        user.setIsActive(command.getIsActive());
        user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        user.setUpdateUserId(command.getUpdateUserId());
        userRepository.update(user);

    }
}
