package cooperate.app.business.user.login;

import cooperate.infrastructure.mediation.IResponse;

public class LoginDto implements IResponse {
    public boolean IsSuccess;
    public long UserId;
    public String Fullname;
    public byte[] Password;
    public int RoleId;
    public String RoleName;
    public String RoleCode;
}
