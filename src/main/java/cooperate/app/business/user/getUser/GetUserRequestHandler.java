package cooperate.app.business.user.getUser;

import cooperate.app.data.read.UserReadRepository;
import cooperate.app.entity.User;
import cooperate.infrastructure.dto.UserDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserRequestHandler implements IHandleRequest<GetUserRequest, UserDto> {

    @Autowired
    UserReadRepository userReadRepository;

    public UserDto Handle(GetUserRequest request) throws Exception {
        User user = userReadRepository.findOne(request.getUserId());
        if (user == null)
            throw new CoopException("error.userNotFound");
        UserDto dto = new UserDto();
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setIsActive(user.getIsActive());
        dto.setUserId(user.getUserId());
        dto.setRoleId(user.getRoleId());
        return dto;

    }
}
