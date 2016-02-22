package cooperate.app.business.login;

import cooperate.app.data.read.UserReadRepository;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginRequestHandler implements IHandleRequest<LoginRequest, LoginResponse> {
    @Autowired
    private UserReadRepository userReadRepository;

    public LoginResponse Handle(LoginRequest request) throws Exception {
        LoginDto dto = userReadRepository.login(request.getEmail(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setSuccess(dto.IsSuccess);
        return response;
    }
}
