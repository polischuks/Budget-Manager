package budget;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class SortCertainType implements SortedType {
    @Override
    public void sorted(List<Purchase> list) {

        int t = 0;
        StringBuilder out = new StringBuilder();
        Scanner sc1 = new Scanner(System.in);
        System.out.println("\nChoose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other");
        t = Integer.parseInt(sc1.next());
        AtomicReference<Double> total = new AtomicReference<>(0.00);
        Comparator<Purchase> comparator = Comparator.comparing(Purchase::getPricePurchased, Comparator.reverseOrder());
        int finalT = t;
        list.stream().filter(purchase -> Objects.equals(purchase.getCategory(), TypeCategory.values()[finalT -1].getType()))
                .sorted(comparator).forEach(purchase -> out.append(purchase.toString()).append("\n"));
        list.forEach(purchase -> total.updateAndGet(v -> v + purchase.pricePurchased));
        if (total.get() > 0) {
            System.out.println("\n" + TypeCategory.values()[t-1].getType() + ":");
            System.out.print(out);
            System.out.printf("Total sum: $%.2f\n", total.get());
        } else {
            System.out.println("\nThe purchase list is empty!");
        }
    }
}
