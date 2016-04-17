package cooperate.app.data.read;

import cooperate.app.entity.SaleType;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.saleType.SaleTypeFilterDto;
import cooperate.infrastructure.dto.saleType.SaleTypeListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleTypeReadRepository extends GenericReadRepository<SaleType> {

    public SaleTypeReadRepository() {
        setClazz(SaleType.class);
    }

    public ListResponse<SaleTypeListDto> ListSaleTypes(ListRequest<SaleTypeFilterDto, SaleTypeListDto> request) throws Exception {

        ListResponse<SaleTypeListDto> saleTypes = new ListResponse<SaleTypeListDto>();
        List<SaleTypeListDto> list = new ArrayList<SaleTypeListDto>();
        list = exetuteListReader(SaleTypeListDto.class, "p_list_sale_type_grid",
                request.getFilter().getName(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        saleTypes.setData(list);
        saleTypes.setDraw(request.getDraw());
        saleTypes.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        saleTypes.setRecordsTotal((Long) getOutputValue("p_total"));
        return saleTypes;
    }
}
