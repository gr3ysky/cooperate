package cooperate.app.data.write;

import cooperate.app.entity.ProducerImage;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerImageRepository extends GenericRepository<ProducerImage> {
    public ProducerImageRepository() {
        setClazz(ProducerImage.class);
    }
}
