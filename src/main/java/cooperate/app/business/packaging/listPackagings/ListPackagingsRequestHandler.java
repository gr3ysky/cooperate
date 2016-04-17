package cooperate.app.business.packaging.listPackagings;

import cooperate.app.data.read.PackagingReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.packaging.PackagingFilterDto;
import cooperate.infrastructure.dto.packaging.PackagingListDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListPackagingsRequestHandler implements IHandleRequest<ListRequest<PackagingFilterDto, PackagingListDto>, ListResponse<PackagingListDto>> {

    @Autowired
    PackagingReadRepository packagingReadRepository;

    public ListResponse<PackagingListDto> Handle(ListRequest<PackagingFilterDto, PackagingListDto> request) throws Exception {
        return packagingReadRepository.ListPackagings(request);
    }
}
