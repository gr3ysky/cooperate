package cooperate.app.business.user.adduser;

import cooperate.app.data.read.UserReadRepository;
import cooperate.app.data.write.UserRepository;
import cooperate.app.entity.User;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import cooperate.infrastructure.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AddUserCommandHandler implements IHandleCommand<AddUserCommand> {
    @Autowired
    ApplicationContext context;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReadRepository userReadRepository;

    public void Handle(AddUserCommand command) throws Exception {

        if (userReadRepository.emailExists(command.getEmail())) {
            throw new CoopException("error.emailExits", command.getEmail());
        }

        User user = new User();
        user.setEmail(command.getEmail());
        user.setFullName(command.getFullName());
        user.setPassword(SecurityHelper.Encrypt(command.getPassword()));
        user.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setRoleId(command.getRoleId());
        user.setCreateUserId(command.getCreateUserId());
        userRepository.create(user);
        UserCreatedEvent event = (UserCreatedEvent) context.getBean("userCreatedEvent");
        event.User = user;
        user.domainEvents.raise(event);
    }
}
