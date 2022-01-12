package budget;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SortedInterface {

    private final List<Purchase> listToSort;

    public SortedInterface(List<Purchase> listToSort) {
        this.listToSort = listToSort;
    }

    void run() {
        Sort sortPurchases = new Sort();
        Scanner sc2 = new Scanner(System.in);
        String chose;
        do {
            System.out.println("\n" +
                    "How do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");
            chose = sc2.next();
            switch (chose) {
                case "1": {
                    sortPurchases.setSortType(new SortAllPurchases());
                    sortPurchases.sorted(listToSort);
                    continue;
                }
                case "2": {
                    sortPurchases.setSortType(new SortByType());
                    sortPurchases.sorted(listToSort);
                    continue;
                }
                case "3": {
                    sortPurchases.setSortType(new SortCertainType());
                    sortPurchases.sorted(listToSort);
                    continue;
                }
                case "4": {
                     System.out.println();
                     return;
                }
            }
        } while (!Objects.equals(chose, "4"));
    }
}
