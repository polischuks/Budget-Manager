package budget;

import static java.lang.String.format;

public class Purchase {
    String category;
    String namePurchased;
    Double pricePurchased;

    Purchase(String s, String s2, Double i) {
        this.category = s;
        this.namePurchased = s2;
        this.pricePurchased = i;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNamePurchased() {
        return namePurchased;
    }

    public void setNamePurchased(String namePurchased) {
        this.namePurchased = namePurchased;
    }

    public Double getPricePurchased() {
        return pricePurchased;
    }

    public void setPricePurchased(Double pricePurchased) {
        this.pricePurchased = pricePurchased;
    }

    @Override
    public String toString() {
        String s = format("%.2f",pricePurchased);
        return namePurchased + " $" + s;
    }

    public String toSave() {
        return category + "--" + namePurchased + "--" + pricePurchased;
    }
}
