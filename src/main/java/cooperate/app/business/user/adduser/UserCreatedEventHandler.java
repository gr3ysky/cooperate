package cooperate.app.business.user.adduser;

import cooperate.infrastructure.mediation.IHandleEvent;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventHandler implements IHandleEvent<UserCreatedEvent> {
    public void Handle(UserCreatedEvent event) {

    }
}
