package cooperate.app.data.read;


import cooperate.app.business.user.login.LoginDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadRepository extends Database {
    public LoginDto getLoginDtobyEmail(String email) throws Exception {
        LoginDto loginDto = exetuteReader(LoginDto.class, "p_get_user_by_email", email);
        return loginDto;
    }

}
