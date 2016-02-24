package cooperate.app.business.user.login;

import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDtoRequest implements IRequest<LoginDtoRequest, LoginDto> {
    @Autowired
    private LoginDtoRequestHandler handler;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IHandleRequest getHandler() {
        return handler;
    }
}
