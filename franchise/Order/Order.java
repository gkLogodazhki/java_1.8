package franchise.Order;

import org.jetbrains.annotations.NotNull;

public class Order implements Comparable<Order> {

    private double price;
    private int countClients; // 1-30
    private int table;
    private boolean result;

    public Order(double price, int countClients) {
        this.price = price;
        this.countClients = countClients;
    }
    public boolean getResult(){
        return this.result;
    }
    public void setResult(Boolean result){
        this.result = result;
    }
    public void setTable(int table){
        this.table = table;
    }

    public int getCountClients(){
        return this.countClients;
    }

    public double getPrice(){
        return this.price;
    }

    @Override
    public int compareTo(@NotNull Order o) {
        if (this.countClients == o.countClients){
            return this.table - o.table;
        }
        return this.countClients - o.countClients;
    }

    @Override
    public String toString() {
        return "Order{" + ", countClients=" + countClients + ", table=" + table + ", price=" +this.price +'}';
    }
}
