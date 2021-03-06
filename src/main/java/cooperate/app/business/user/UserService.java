package cooperate.app.business.user;

import cooperate.app.ServiceBase;
import cooperate.app.business.user.ListUsers.ListUsersRequestHandler;
import cooperate.app.business.user.activateUser.ActivateUserCommand;
import cooperate.app.business.user.adduser.AddUserCommand;
import cooperate.app.business.user.getUser.GetUserRequest;
import cooperate.app.business.user.login.LoginDto;
import cooperate.app.business.user.login.LoginDtoRequest;
import cooperate.app.business.user.login.LoginRequest;
import cooperate.app.business.user.login.LoginResponse;
import cooperate.app.business.user.updateUser.UpdateUserCommand;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.user.UserDto;
import cooperate.infrastructure.dto.user.UserFilterDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends ServiceBase {
    @Autowired
    private Mediator _mediator;


    @Transactional(readOnly = true)
    public LoginResponse Login(LoginRequest request) throws Exception {
        LoginResponse response = _mediator.request(request);
        return response;

    }

    @Transactional(readOnly = true)
    public LoginDto getUserByEmail(LoginDtoRequest request) throws Exception {
        return _mediator.request(request);
    }

    @Transactional
    public void AddUser(AddUserCommand command) throws Exception {
        _mediator.send(command);

    }

    @Transactional(readOnly = true)
    public ListResponse<UserDto> ListUsers(ListRequest<UserFilterDto, UserDto> request) throws Exception {
        request.setHandler((ListUsersRequestHandler) context.getBean("listUsersRequestHandler"));
        return _mediator.request(request);
    }

    @Transactional
    public void ActivateUser(ActivateUserCommand command) throws Exception {
        _mediator.send(command);
    }

    @Transactional(readOnly = true)
    public UserDto GetUserDto(GetUserRequest request) throws Exception {
        return _mediator.request(request);
    }

    @Transactional
    public void UpdateUser(UpdateUserCommand command) throws Exception {
        _mediator.send(command);
    }
}
