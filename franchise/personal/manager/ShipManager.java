package franchise.personal.manager;

import franchise.Restaurant;
import franchise.personal.waiter.Waiter;

public class ShipManager extends Manager implements IManager{

    private Restaurant restaurant;

    public ShipManager(String name, double salary) {
        super(name, IPersonalKind.Manager.SHIP_MANAGER, salary ,0.04);
    }

    /*
    Ако ​​служителя ​​е ​​мениджър-смяна​– операцията „нахокай сервитьора“ - да се намери от всички
    служители сервитьори в същият ресторант, този с най-много лошо изпълнени поръчки и да
    се нахока и махне от ресторанта. Да се изведе на екрана данни за уволнение служител и
    колко грешки е допуснал. (10%)
     */
    public void findWorstWaiter(){
        Waiter w = this.getFranchise().getTheWorstWaiter(restaurant);
        System.out.println(this + ", " + w + " You are the worst waiter i ever seen");
        System.out.println(w + " made " + w.getErrors() + " errors");
        this.getFranchise().fireHim(restaurant,w);
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant){
        if (restaurant != null){
            this.restaurant = restaurant;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double getRev() {
        return this.restaurant.getRev();
    }

    public void printReport() {
        System.out.println(this);
        this.restaurant.printReportError();
    }

    @Override
    public void calculateSalary() {
        this.setSalary(4*this.getRev()/100);
    }

}
