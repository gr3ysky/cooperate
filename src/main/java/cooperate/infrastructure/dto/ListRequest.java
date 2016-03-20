package cooperate.infrastructure.dto;


import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;

public class ListRequest<T, TResponse> implements IRequest<ListRequest<T, TResponse>, ListResponse<TResponse>> {
    private int draw;
    private int pageSize;
    private int start;
    private String orderColumn;
    private String orderDir;
    private T filter;
    private IHandleRequest<ListRequest<T, TResponse>, ListResponse<TResponse>> handler;
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public IHandleRequest<ListRequest<T, TResponse>, ListResponse<TResponse>> getHandler() {
        return handler;
    }

    public void setHandler(IHandleRequest<ListRequest<T, TResponse>, ListResponse<TResponse>> handler) {
        this.handler = handler;
    }
}
