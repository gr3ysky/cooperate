package cooperate.infrastructure.exception;

public class CoopException extends Exception {

    private String message;
    private Object[] params;

    public CoopException(String message, Object... params) {
        super(message);
        this.message = message;
        if (params != null)
            this.params = params;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
