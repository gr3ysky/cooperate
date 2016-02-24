package cooperate.app.business.user.login;

import cooperate.infrastructure.mediation.IResponse;

public class LoginResponse implements IResponse {
    private boolean IsSuccess;
    private LoginDto loginDto;

    public LoginDto getLoginDto() {
        return loginDto;
    }

    public void setLoginDto(LoginDto loginDto) {
        this.loginDto = loginDto;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

}

