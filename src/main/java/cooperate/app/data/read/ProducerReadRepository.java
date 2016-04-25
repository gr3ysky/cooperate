package cooperate.app.data.read;

import cooperate.app.entity.Producer;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProducerReadRepository extends GenericReadRepository<Producer> {
    public ProducerReadRepository() {
        setClazz(Producer.class);
    }

    public ListResponse<ProducerListDto> ListProducers(ListRequest<ProducerFilterDto, ProducerListDto> request) throws Exception {
        ListResponse<ProducerListDto> producers = new ListResponse<ProducerListDto>();
        List<ProducerListDto> list = new ArrayList<ProducerListDto>();
        list = exetuteListReader(ProducerListDto.class, "p_list_producers_grid",
                request.getFilter().getName(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        producers.setData(list);
        producers.setDraw(request.getDraw());
        producers.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        producers.setRecordsTotal((Long) getOutputValue("p_total"));
        return producers;
    }

    public List<SelectListItem> getProducersDropDown() throws Exception {
        return exetuteListReader(SelectListItem.class, "p_dropdown_producer");

    }
}
