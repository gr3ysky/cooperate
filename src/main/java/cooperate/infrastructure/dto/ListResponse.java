package cooperate.infrastructure.dto;

import java.util.List;

public class ListResponse<T> {
    private List<T> data;
    private long totalCount;
    private long filteredCount;
    private int draw;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotal(long total) {
        this.totalCount = total;
    }

    public long getFilteredCount() {
        return filteredCount;
    }

    public void setFilteredCount(long filteredCount) {
        this.filteredCount = filteredCount;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
