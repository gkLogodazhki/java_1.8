package franchise;

import franchise.Order.Order;
import franchise.personal.Personal;
import java.util.*;

import franchise.personal.waiter.Waiter;
import org.jetbrains.annotations.NotNull;

public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private TreeMap<Integer, Personal> tables;
    private HashMap<Boolean,TreeSet<Order>> orders;
    private double rev;

    public double getRev(){
        double rev = 0;
        for (Map.Entry<Boolean,TreeSet<Order>> entry : this.orders.entrySet()){
            for (Order o : entry.getValue()){
                rev += o.getPrice();
            }
        }
        this.rev = rev;
        return this.rev;
    }

    public Map getOrders(){
        return Collections.unmodifiableMap(this.orders);
    }

    public int getCountErrors(){
        return this.orders.get(true).size();
    }
    
    public boolean addOrder(Franchise f, Order order, Personal.IPersonalKind kind){
        while (true){
            int number = new Random().nextInt(this.tables.keySet().size());
            if (this.tables.get(number) == null){
                order.setTable(number);
                break;
            }
        }
        if (!this.orders.containsKey(order.getResult())){
            this.orders.put(order.getResult(), new TreeSet<>());
        }

        if (this.orders.get(order.getResult()).add(order)){
            Waiter w = f.getWaiter(this, kind);
            w.takeOrder(order);
            return true;
        } else {
            return false;
        }

    }

    public Restaurant(String name, int countTables){
        this.name = name;
        this.tables = new TreeMap<>();
        this.orders = new HashMap<>();
        for (int i = 0; i < countTables; i++){
            this.tables.put(i,null);
        }
    }

    public String getName() {
        return name;
    }

    public void printReportError() {
        if (this.orders.get(false).isEmpty()){
            return;
        }
        for (Order o : this.orders.get(false)){
            System.out.println(o);
        }
    }

    @Override
    public int compareTo(@NotNull Restaurant o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
