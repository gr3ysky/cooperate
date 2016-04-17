package cooperate.app.business.packaging.getPackaging;

import cooperate.infrastructure.dto.packaging.PackagingDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPackagingRequest implements IRequest<GetPackagingRequest, PackagingDto> {
    @Autowired
    GetPackagingRequetsHandler handler;
    private int packagingId;

    public IHandleRequest<GetPackagingRequest, PackagingDto> getHandler() {
        return handler;
    }

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }
}
