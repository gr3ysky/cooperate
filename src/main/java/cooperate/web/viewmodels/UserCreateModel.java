package cooperate.web.viewmodels;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserCreateModel implements Serializable {
    @Resource(name = "messages", mappedName = "label.fullName")
    private int userId;

    @NotEmpty
    @Length(max = 100)
    @Resource(name = "messages", mappedName = "label.fullName")
    private String fullName;

    @NotEmpty
    @Email
    @Length(max = 80)
    @Pattern(regexp = ".*@boun.edu.tr$")
    private String email;

    @Resource(name = "messages", mappedName = "label.isActive")
    private Boolean isActive;

    @NotNull
    @Resource(name = "messages", mappedName = "label.role")
    private int roleId;

    @NotEmpty
    @Size(min = 6, max = 12)
    @Resource(name = "messages", mappedName = "label.password")
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
