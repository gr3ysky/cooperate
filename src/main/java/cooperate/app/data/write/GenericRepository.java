package cooperate.app.data.write;

import cooperate.app.entity.EntityBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository<T extends EntityBase> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public final void setClazz(Class<T> tEntityClass) {
        this.entityClass = tEntityClass;
    }

    private T findOne(String id) {
        return getCurrentSession().get(entityClass, id);
    }

    public void create(T entity) {
        getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(String entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
