package mockito;

public class ShopRepository {

    public Shop findShop() {
        /* ta metoda z premedytacją nic nie zwraca. W prawdziwym wypadku byłoby tutaj połączenie z bazą danych
        * która zwraca aktualny stan z bazy. W tym zadaniu chodzi o to, żeby przygotować dane testowe które nie pochodzą
         * z bazy danych */
        return null;
    }

}
