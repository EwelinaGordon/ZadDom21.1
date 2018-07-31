package mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShopControllerTest {

    @Mock
    ShopRepository shopRepository;

    private ShopController shopController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        stock.put(new Item("Ciastka", 5, 2, true), 1);
        Shop shop = new Shop(0, stock);
        when(shopRepository.findShop()).thenReturn(shop);
        shopController = new ShopController(shopRepository);

    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungling() {
        // given
        Human human = new Human();
        // when
        shopController.sellItem(human, "Piwo");
    }

    @Test
    public void shouldBeSoundAfterSuccessSell() {
        // given
        Human human = new Human();
        human.setAge(34);
        human.setMoney(40);

        Item item = new Item("Piwo", 18, 4, true);
        Shop shop = Mockito.mock(Shop.class);
        when(shop.hasItem("Piwo")).thenReturn(true);
        when(shop.findItemByName("Piwo")).thenReturn(item);

        Mockito.when(shopRepository.findShop()).thenReturn(shop);
        shopController = new ShopController(shopRepository);

        // when
        shopController.sellItem(human, "Piwo");

        verify(shop).playCashSound();
    }

    @Test
    public void shouldTransferMoneyFromHumanToShopAfterSuccessSell() {
        // given
        Human human = new Human();
        human.setMoney(100);
        human.setAge(34);
        // when
        shopController.sellItem(human, "Piwo");

        Shop shop = shopController.getShop();

        Assert.assertThat(human.getMoney(), CoreMatchers.is(96));
        Assert.assertThat(shop.getMoney(), CoreMatchers.is(4));
    }

    @Test
    public void shouldDecreaseAmountOfProductAfterSuccessSell() {
        // given
        Human human = new Human();
        human.setMoney(100);
        human.setAge(34);

        Integer amountBeforeSell = shopController.getShop().getStock().get(shopController.getShop().findItemByName("Piwo"));

        // when
        shopController.sellItem(human, "Piwo");

        Integer amountAfterSell = shopController.getShop().getStock().get(shopController.getShop().findItemByName("Piwo"));
        Assert.assertNotEquals(amountBeforeSell, amountAfterSell);
        Assert.assertThat(amountAfterSell, CoreMatchers.is(amountBeforeSell - 1));
    }

    @Test
    public void shouldRemoveProductFromStockIfAmountAfterSellIsEqualToZero() {
        // given
        Human human = new Human();
        human.setMoney(100);
        human.setAge(34);

        Integer amountBeforeSell = shopController.getShop().getStock().get(shopController.getShop().findItemByName("Ciastka"));

        // when
        shopController.sellItem(human, "Ciastka");

        Integer amountAfterSell = shopController.getShop().getStock().get(shopController.getShop().findItemByName("Ciastka"));
        Assert.assertNotEquals(amountBeforeSell, amountAfterSell);
        Assert.assertThat(amountAfterSell, CoreMatchers.nullValue());


    }
}


