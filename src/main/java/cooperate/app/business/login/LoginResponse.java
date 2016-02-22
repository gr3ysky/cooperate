package cooperate.app.business.login;

import cooperate.infrastructure.mediation.IResponse;

/**
 * Created by taner on 21.02.2016.
 */
public class LoginResponse implements IResponse {
    private boolean IsSuccess;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

}

