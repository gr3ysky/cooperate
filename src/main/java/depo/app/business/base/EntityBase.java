package depo.app.business.base;

import java.util.UUID;

public class EntityBase {

    private UUID Id;

    public EntityBase() {
        Id = UUID.randomUUID();
    }

    public EntityBase(UUID id) {
        Id = id;
    }

    public UUID getId() {
        return Id;
    }
}
