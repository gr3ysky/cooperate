package demo.web.viewmodels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel {
    @NotNull
    private String email;
    @NotNull
    @Size(min=6)
    private String password;
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
