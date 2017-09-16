package franchise.Order;

import java.time.LocalDateTime;

public class Normal extends Order {

    private LocalDateTime timeSit;

    public Normal(double price, int countClients) {
        super(price, countClients);
        this.timeSit = LocalDateTime.now();
    }

    public LocalDateTime getTimeStay(){
        return LocalDateTime.from(timeSit);
    }
}
