package mockito;

import java.util.Map;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;

    public Shop(int money, Map<Item, Integer> stock) {
        this.money = money;
        this.stock = stock;
    }

    void playCashSound() {
        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) {
        Item itemByName = findItemByName(itemName);
        Integer amountOnStock = getStock().get(itemByName);

        if (amountOnStock != null && amountOnStock > 0) {
            return true;
        }
        return false;
    }

    public Item findItemByName(String itemName) {
        for (Map.Entry<Item, Integer> entry : getStock().entrySet()) {
            if (entry.getKey().getName().equals(itemName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void sellItem(Human human, Item item) {
        human.setMoney(human.getMoney()-item.getPrice());
        setMoney(getMoney() + item.getPrice());
        Map<Item, Integer> stock = getStock();
        Integer amountItems = stock.get(item);
        Integer amountAfterSell = amountItems -1;
        if (amountAfterSell == 0) {
            stock.remove(item);
        } else{
            stock.put(item, amountAfterSell);
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }


}
