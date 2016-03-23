package cooperate.app.business.user.login;

import cooperate.infrastructure.dto.NotMapped;
import cooperate.infrastructure.enums.Role;
import cooperate.infrastructure.mediation.IResponse;

public class LoginDto implements IResponse {
    @NotMapped
    public boolean IsSuccess;
    public int UserId;
    public String Fullname;
    public byte[] Password;
    public int RoleId;
    public String RoleName;
    public String RoleCode;
    @NotMapped
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
