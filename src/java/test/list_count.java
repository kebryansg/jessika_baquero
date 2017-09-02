package test;

import java.util.List;


public class list_count {

    public list_count() {
        this.total = 0; 
    }
    private int total;
    private List<?> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
