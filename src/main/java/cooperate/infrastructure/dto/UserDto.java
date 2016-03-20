package cooperate.infrastructure.dto;

public class UserDto {
    private int UserId;
    private String FullName;
    private String Email;
    private boolean IsActive;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean active) {
        IsActive = active;
    }

}
