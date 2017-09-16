package franchise.Order;

public class Check extends Order {

    private String restaurantName;
    private int countErrors; // 1-10;

    public Check(String restaurantName, double price, int countClients) {
        super(price, countClients);
        this.restaurantName = restaurantName;
    }

}
