package cooperate.app.data.read;

import cooperate.app.entity.ProducerImage;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producerImage.ProducerImageFilterDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProducerImageReadRepository extends GenericReadRepository<ProducerImage> {
    public ProducerImageReadRepository() {
        setClazz(ProducerImage.class);
    }

    public ListResponse<ProducerImageListDto> ListProducerImages(ListRequest<ProducerImageFilterDto, ProducerImageListDto> request) throws Exception {
        ListResponse<ProducerImageListDto> images = new ListResponse<ProducerImageListDto>();
        List<ProducerImageListDto> list = new ArrayList<ProducerImageListDto>();
        list = exetuteListReader(ProducerImageListDto.class, "p_list_producer_images_grid",
                request.getFilter().getProducerId(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        images.setData(list);
        images.setDraw(request.getDraw());
        images.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        images.setRecordsTotal((Long) getOutputValue("p_total"));
        return images;
    }
}
