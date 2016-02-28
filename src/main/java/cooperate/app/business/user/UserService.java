package cooperate.app.business.user;

import cooperate.app.ServiceBase;
import cooperate.app.business.user.adduser.AddUserCommand;
import cooperate.app.business.user.login.LoginDto;
import cooperate.app.business.user.login.LoginDtoRequest;
import cooperate.app.business.user.login.LoginRequest;
import cooperate.app.business.user.login.LoginResponse;
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
}
