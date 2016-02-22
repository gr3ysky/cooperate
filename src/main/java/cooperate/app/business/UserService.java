package cooperate.app.business;

import cooperate.app.business.adduser.AddUserCommand;
import cooperate.app.business.login.LoginRequest;
import cooperate.app.business.login.LoginResponse;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private Mediator _mediator;

    @Transactional(readOnly = true)
    public LoginResponse Login(LoginRequest request) throws Exception {
        LoginResponse response = _mediator.request(request);
        return response;

    }

    @Transactional
    public void AddUser(AddUserCommand command) throws Exception {
        _mediator.send(command);

    }
}
