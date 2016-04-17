package cooperate.app.data.write;

import cooperate.app.entity.Producer;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerRepository extends GenericRepository<Producer> {
    public ProducerRepository() {
        setClazz(Producer.class);
    }
}
