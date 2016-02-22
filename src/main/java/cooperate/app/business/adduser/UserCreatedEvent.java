package cooperate.app.business.adduser;

import cooperate.app.entity.User;
import cooperate.infrastructure.mediation.IEvent;
import cooperate.infrastructure.mediation.IHandleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEvent implements IEvent {
    public User User;
    @Autowired
    private UserCreatedEventHandler handler;

    public IHandleEvent<UserCreatedEvent> getHandler() {
        return handler;
    }
}
