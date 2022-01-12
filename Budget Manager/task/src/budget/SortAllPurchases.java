package budget;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SortAllPurchases implements SortedType{

    @Override
    public void sorted(List<Purchase> list) {
        if (list.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }
        AtomicReference<Double> total = new AtomicReference<>(0.00);
        Comparator<Purchase> comparator = Comparator.comparing(Purchase::getPricePurchased, Comparator.reverseOrder());
        System.out.println("\nAll:");
        list.stream().sorted(comparator).forEach(purchase -> System.out.println(purchase.toString()));
        list.forEach(purchase -> total.updateAndGet(v -> v + purchase.pricePurchased));
        System.out.printf("Total: $%.2f\n", total.get());
    }
}
