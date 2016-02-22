package cooperate.app.business.adduser;

import cooperate.infrastructure.mediation.IHandleEvent;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventHandler implements IHandleEvent<UserCreatedEvent> {
    public void Handle(UserCreatedEvent event) {

    }
}
