package cooperate.app.business.user.login;

import cooperate.app.data.read.UserReadRepository;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDtoRequestHandler implements IHandleRequest<LoginDtoRequest, LoginDto> {
    @Autowired
    private UserReadRepository userReadRepository;

    public LoginDto Handle(LoginDtoRequest request) throws Exception {
        return userReadRepository.getLoginDtobyEmail(request.getEmail());
    }
}
