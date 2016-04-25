package cooperate.app.business.packaging;

import cooperate.app.ServiceBase;
import cooperate.app.business.packaging.listPackagings.ListPackagingsRequestHandler;
import cooperate.app.data.read.PackagingReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.packaging.PackagingDto;
import cooperate.infrastructure.dto.packaging.PackagingFilterDto;
import cooperate.infrastructure.dto.packaging.PackagingListDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IRequest;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PackagingService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Autowired
    PackagingReadRepository packagingReadRepository;

    @Transactional(readOnly = true)
    public List<SelectListItem> getPackagingDropdown() throws Exception {
        return packagingReadRepository.getPackagingDropdown();
    }


    @Transactional
    public void AddPackaging(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivatePackaging(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public PackagingDto GetPackagingDto(IRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void UpdatePackaging(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional(readOnly = true)
    public ListResponse<PackagingListDto> ListPackagings(ListRequest<PackagingFilterDto, PackagingListDto> request) throws Exception {
        request.setHandler((ListPackagingsRequestHandler) context.getBean("listPackagingsRequestHandler"));
        return mediator.request(request);
    }

}
