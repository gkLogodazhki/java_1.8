package franchise.personal.waiter;

import franchise.Restaurant;
import franchise.Order.Check;
import franchise.Order.Order;
import franchise.personal.Personal;

import java.util.*;

public abstract class Waiter extends Personal implements IWaiter {

    private Restaurant restaurant;
    private HashMap<Boolean,TreeSet<Order>> ordersArchive;
    private HashSet<Order> orders;


    public int allClients(){
        int countClients = 0;
        this.ordersArchive = new HashMap<>();
        for (Order o : orders){
            countClients += o.getCountClients();
        }
        this.orders = new HashSet<>();
        return countClients;
    }

    public Order removeOrder(Order order){
        Order o = null;
        if (orders.contains(order)){
            o = order;
            orders.remove(order);
        }
        return o;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    protected Restaurant getRestaurant(){
        return this.restaurant;
    }

    public Waiter(String name, IPersonalKind kind, double salary,double percent) {
        super(name, PersonalType.WAITER ,kind, salary, percent);
        this.ordersArchive = new HashMap<>();
        this.orders = new HashSet<>();
    }
    /*
    Да​​ изпълнява​ ​поръчка​– да се изпише на конзолата, че „служител (име) изпълнява поръчка
    (заглавие).“, както и да се изведе дали правилно е изпълнена поръчката или е
    имало оплакване към служителя. Вероятността за грешки зависи от броя клиенти умножено
    по масите които обслужва един сервитьор и разделено на степента на служителя – колкото
    по-старши е служителя, толкова по-малко вероятно е да да допусне грешка. (измислете
    сами формула в зависимост от бройката на клиентите, бройката на масите и нивото на
    служителя да получите вероятност от 1% до 50% и при такава вероятност поръчката да се
    сгреши). Ако поръчката е оплескана – броя сгрешени поръчки се увеличава за съответния
    служител. Ако поръчката е от тип “проверка” тогава обслужващия сервитьор има 20% шанс
    да обърка поръчката и тогава тя се води сгрешена. (10%)
     */
    public void takeOrder(Order order){
        if (new Random().nextBoolean()) {
            this.getFranchise().giveOrderTo(restaurant,order);
            return;
        }
        if (orders.contains(order)){
            return;
        }
        this.orders.add(order);
        boolean rightOrder = ((orders.size()*allClients()*2)%100 < new Random().nextInt(100)+1)? true : false;
        String orderResult = rightOrder ? " The check is right" : " The check is not right";

        if (order instanceof Check && new Random().nextInt(5) == 0){
            rightOrder = false;
        }

        if (!rightOrder){
            this.decreaseSalary(20);
            this.getFranchise().decreaseManagerMoney(restaurant,10);
        }

        if (!this.ordersArchive.containsKey(rightOrder)){
            this.ordersArchive.put(rightOrder, new TreeSet<>());
        }

        order.setResult(rightOrder);
        this.ordersArchive.get(rightOrder).add(order);
        this.orders.remove(order);

        System.out.println(this + " took order " + order + orderResult);
    }

    @Override
    public double getRev() {
        int rev = 0;
        for (Map.Entry<Boolean,TreeSet<Order>> entryBoolean : this.ordersArchive.entrySet()){
            for (Order o : entryBoolean.getValue()){
                rev += o.getPrice();
            }
        }
        return rev;
    }

    public int getErrors(){
        if (this.ordersArchive.containsKey(false)){
            return this.ordersArchive.get(false).size();
        } else {
            return 0;
        }
    }

    @Override
    public void printReport() {
        if (this.ordersArchive == null || !this.ordersArchive.containsKey(false)){
            return;
        }
        for (Order o : this.ordersArchive.get(false)){
            System.out.println(o);
        }
    }

    @Override
    public String toString() {
        return "Waiter{" + super.toString() +
                ", rev=" + this.getRev() +
                '}';
    }
}
