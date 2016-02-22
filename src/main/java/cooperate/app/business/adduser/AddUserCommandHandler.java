package cooperate.app.business.adduser;

import cooperate.app.data.write.UserRepository;
import cooperate.app.entity.User;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AddUserCommandHandler implements IHandleCommand<AddUserCommand> {
    @Autowired
    ApplicationContext context;
    @Autowired
    private UserRepository userRepository;

    public void Handle(AddUserCommand command) {
        User user = new User();
        user.setEmail(command.getEmail());
        user.setFullname(command.getFullName());
        user.setPassword(command.getPassword());
        user.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
        userRepository.create(user);
        UserCreatedEvent event = (UserCreatedEvent) context.getBean("userCreatedEvent");
        event.User = user;
        user.domainEvents.raise(event);
    }
}
