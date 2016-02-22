package cooperate.app.data.write;

import cooperate.app.business.base.EntityBase;
import cooperate.infrastructure.mediation.IEvent;
import cooperate.infrastructure.mediation.Mediator;
import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;


public class PublishEventsInterceptor extends EmptyInterceptor {
    @Autowired
    private Mediator _mediator;

    @Override
    public void postFlush(Iterator entities) {
        while (entities.hasNext()) {
            EntityBase entity = (EntityBase) entities.next();
            ArrayList<IEvent> events = entity.domainEvents.getAndClear();
            for (IEvent event : events) {
                _mediator.publish(event);
            }
        }
        super.postFlush(entities);
    }
}
