package budget;

public enum TypeCategory {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other");

    public final String type;

    TypeCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
