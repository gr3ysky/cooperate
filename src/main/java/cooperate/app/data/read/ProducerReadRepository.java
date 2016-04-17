package cooperate.app.data.read;

import cooperate.app.entity.Producer;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerReadRepository extends GenericReadRepository<Producer> {
    public ProducerReadRepository() {
        setClazz(Producer.class);
    }

    public ListResponse<ProducerListDto> ListProducers(ListRequest<ProducerFilterDto, ProducerListDto> request) {
        return null;
    }
}
