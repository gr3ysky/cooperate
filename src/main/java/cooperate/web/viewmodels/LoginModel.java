package cooperate.web.viewmodels;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Resource;
import javax.validation.constraints.Size;

public class LoginModel {
    @NotEmpty
    @Email
    @Resource(name = "messages", mappedName = "label.email")
    private String email;
    @NotEmpty
    @Size(min = 6, max = 12)
    @Resource(name = "messages", mappedName = "label.password")
    private String password;
    @Resource(name = "messages", mappedName = "label.rememberMe")
    private Boolean rememberMe;

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
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
}
