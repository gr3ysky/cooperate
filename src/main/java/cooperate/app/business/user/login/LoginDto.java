package cooperate.app.business.user.login;

import cooperate.infrastructure.mediation.IResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class LoginDto implements IResponse {
    public boolean IsSuccess;
    public long UserId;
    public String Fullname;
    public byte[] Password;
    public int RoleId;
    public String RoleName;
    public String RoleCode;
}
