package cooperate.web.viewmodels;

public class GenericAjaxResponse<T> extends AjaxResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
