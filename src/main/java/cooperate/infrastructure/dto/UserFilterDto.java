package cooperate.infrastructure.dto;

/**
 * Created by Taner on 18.03.2016.
 */
public class UserFilterDto {

    private String fullname;
    private int isActive;
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int active) {
        isActive = active;
    }
}
