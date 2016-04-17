package cooperate.app.data.read;

import cooperate.app.entity.Packaging;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.packaging.PackagingFilterDto;
import cooperate.infrastructure.dto.packaging.PackagingListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PackagingReadRepository extends GenericReadRepository<Packaging> {
    public PackagingReadRepository() {
        setClazz(Packaging.class);
    }

    public ListResponse<PackagingListDto> ListPackagings(ListRequest<PackagingFilterDto, PackagingListDto> request) throws Exception {
        ListResponse<PackagingListDto> packagings = new ListResponse<PackagingListDto>();
        List<PackagingListDto> list = new ArrayList<PackagingListDto>();
        list = exetuteListReader(PackagingListDto.class, "p_list_packaging_grid",
                request.getFilter().getName(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        packagings.setData(list);
        packagings.setDraw(request.getDraw());
        packagings.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        packagings.setRecordsTotal((Long) getOutputValue("p_total"));
        return packagings;

    }
}