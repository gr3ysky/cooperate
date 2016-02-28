package cooperate.app.business.user.login;

import cooperate.app.data.read.UserReadRepository;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LoginRequestHandler implements IHandleRequest<LoginRequest, LoginResponse> {
    @Autowired
    private UserReadRepository userReadRepository;
    public LoginResponse Handle(LoginRequest request) throws Exception {
        LoginDto dto = userReadRepository.getLoginDtobyEmail(request.getEmail());
        if (dto == null)
            throw new Exception("User is not found");
        dto.setRole();
        LoginResponse response = new LoginResponse();
        response.setLoginDto(dto);
        byte[] passwordHash = SecurityHelper.Encrypt(request.getPassword());
        response.setSuccess(Arrays.equals(passwordHash, dto.Password) ? true : false);
        return response;
    }
}
