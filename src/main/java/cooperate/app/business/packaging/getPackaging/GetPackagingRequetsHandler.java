package cooperate.app.business.packaging.getPackaging;

import cooperate.app.data.read.PackagingReadRepository;
import cooperate.app.entity.Packaging;
import cooperate.infrastructure.dto.packaging.PackagingDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPackagingRequetsHandler implements IHandleRequest<GetPackagingRequest, PackagingDto> {
    @Autowired
    PackagingReadRepository packagingReadRepository;

    public PackagingDto Handle(GetPackagingRequest request) throws Exception {
        Packaging packaging = packagingReadRepository.findOne(request.getPackagingId());
        if (packaging == null)
            throw new CoopException("error.packagingNotFound");
        PackagingDto dto = new PackagingDto();
        dto.setPackagingId(packaging.getPackagingId());
        dto.setIsActive(packaging.getIsActive());
        dto.setName(packaging.getName());
        dto.setResourceKey(packaging.getResourceKey());
        return dto;
    }
}
