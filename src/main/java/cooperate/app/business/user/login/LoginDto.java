package cooperate.app.business.user.login;

import cooperate.infrastructure.enums.Role;
import cooperate.infrastructure.mediation.IResponse;

public class LoginDto implements IResponse {
    public boolean IsSuccess;
    public long UserId;
    public String Fullname;
    public byte[] Password;
    public int RoleId;
    public String RoleName;
    public String RoleCode;
    public Role Role;

    public void setRole() {
        switch (RoleId) {
            case 1:
                this.Role = cooperate.infrastructure.enums.Role.SuperUser;
            case 2:
                this.Role = cooperate.infrastructure.enums.Role.Admin;
            default:
                this.Role = cooperate.infrastructure.enums.Role.Member;
        }
    }
}
