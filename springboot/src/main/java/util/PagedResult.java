package util;

import java.util.List;

public class PagedResult<T> {

    public PagedResult(int total, List<T> rows) {
        this.Total = total;
        this.Rows = rows;
    }

    private List<T> Rows;
    private int Total;

    public List<T> getRows() {
        return Rows;
    }

    public void setRows(List<T> rows) {
        this.Rows = rows;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        this.Total = total;
    }
}
