package cooperate.app.business.user.getUser;

import cooperate.infrastructure.dto.user.UserDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserRequest implements IRequest<GetUserRequest, UserDto> {
    @Autowired
    GetUserRequestHandler handler;
    private int userId;

    public IHandleRequest<GetUserRequest, UserDto> getHandler() {
        return handler;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }
}
