package cooperate.infrastructure.mediation;

import java.util.ArrayList;
import java.util.List;

public class DomainEventCollection {
    private final Object mutex = new Object();
    private List<IEvent> domainEvents = new ArrayList<IEvent>() {
    };

    public void raise(IEvent event) {
        synchronized (mutex) {
            domainEvents.add(event);
        }
    }

    public ArrayList<IEvent> getAndClear() {
        ArrayList<IEvent> events = new ArrayList<IEvent>(domainEvents);
        domainEvents.clear();
        return events;
    }
}