package cooperate.app.data.read;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class GenericReadRepository<T> extends Database {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public final void setClazz(Class<T> tEntityClass) {
        this.entityClass = tEntityClass;
    }

    public <K extends Serializable> T findOne(K id) {
        return getCurrentSession().get(entityClass, id);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
