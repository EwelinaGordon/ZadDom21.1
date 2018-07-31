package mockito;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();
    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
            }
            if ("policeman".equals(human.getJob()) && !item.isLegal()) {
                throw new IllegalItemException();
            }
            if (human.getMoney() < item.getPrice()) {
                throw new NotEnoughMoneyException();
            }
            shop.playCashSound();
            shop.sellItem(human, item);
        } else {
            throw new OutOfStockException();
        }
    }

    protected Shop getShop() {
        return this.shop;
    }
}
