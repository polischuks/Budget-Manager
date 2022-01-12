package budget;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SortByType implements SortedType{
    @Override
    public void sorted(List<Purchase> list) {

        List<String[]> byType = new ArrayList<>();
        Map<Double, String> sortEnd = new TreeMap<>(Comparator.reverseOrder());

        for(TypeCategory type: TypeCategory.values()) {
            AtomicReference<Double> totalCategory = new AtomicReference<>(0.00);
            list.stream().filter(purchase -> Objects.equals(purchase.getCategory(), type.getType()))
                    .forEach(purchase -> totalCategory.updateAndGet(v -> v + purchase.getPricePurchased()));
            byType.add(new String[]{type.getType(), String.valueOf(totalCategory)});
            sortEnd.put(totalCategory.get(), type.getType());
        }
        System.out.println("\nTypes:");
        for (Map.Entry<Double, String> entry : sortEnd.entrySet()) {
            System.out.print(entry.getValue() + " - ");
            System.out.printf("$%.2f\n", entry.getKey());
        }
    }
}
