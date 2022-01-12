package budget;

import java.util.List;

public class Sort {

    private SortedType sortType;

    public void setSortType(SortedType sortType) {
        this.sortType = sortType;
    }
    public void sorted(List<Purchase> list) {
        this.sortType.sorted(list);
    }
}
