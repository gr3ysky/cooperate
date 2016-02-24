package cooperate.app.entity;

import cooperate.infrastructure.mediation.DomainEventCollection;

public class EntityBase {
    public DomainEventCollection domainEvents;

    public EntityBase() {
        domainEvents = new DomainEventCollection();
    }
}
