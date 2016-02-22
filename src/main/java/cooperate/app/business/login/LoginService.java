package cooperate.app.business.login;

import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    @Autowired
    private Mediator _mediator;

    @Transactional(readOnly = true)
    public LoginResponse Login(LoginRequest request) throws Exception {
        LoginResponse response = _mediator.request(request);
        return response;

    }
}
