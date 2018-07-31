package mockito;

public class Item {

    private String name;
    private int ageRestriction;
    private int price;
    private boolean isLegal;

    public Item(String name, int ageRestriction, int price, boolean isLegal) {
        this.name = name;
        this.ageRestriction = ageRestriction;
        this.price = price;
        this.isLegal = isLegal;
    }

    public String getName() {
        return name;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public int getPrice() {
        return price;
    }

    public boolean isLegal() {
        return isLegal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return name != null ? name.equals(item.name) : item.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
