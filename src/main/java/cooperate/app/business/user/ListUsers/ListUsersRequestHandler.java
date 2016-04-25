package cooperate.app.business.user.ListUsers;


import cooperate.app.data.read.UserReadRepository;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.user.UserDto;
import cooperate.infrastructure.dto.user.UserFilterDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListUsersRequestHandler implements IHandleRequest<ListRequest<UserFilterDto, UserDto>, ListResponse<UserDto>> {
    @Autowired
    private UserReadRepository userReadRepository;

    public ListResponse<UserDto> Handle(ListRequest<UserFilterDto, UserDto> request) throws Exception {
        return userReadRepository.ListUsers(request);

    }
}
