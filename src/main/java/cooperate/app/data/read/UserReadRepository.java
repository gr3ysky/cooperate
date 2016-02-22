package cooperate.app.data.read;


import cooperate.app.business.login.LoginDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadRepository extends Database {
    public LoginDto login(String email, String password) throws Exception {
        LoginDto loginDto = exetuteReader(LoginDto.class, "p_login", email, password);
        return loginDto;
    }

}
