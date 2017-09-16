package franchise.personal.manager;

import franchise.Restaurant;
import franchise.personal.Personal;

import java.util.TreeSet;

public class RegionalManager extends Manager implements IManager {

    private TreeSet<Restaurant> restaurants;

    public RegionalManager(String name, double salary) {
        super(name, IPersonalKind.Manager.REGIONAL_MANAGER, salary,0.06);
        this.restaurants = new TreeSet<>();
    }

    public int getCountRestaurants(){
        return this.restaurants.size();
    }

    @Override
    public boolean addRestaurant(Restaurant r) {
        if (this.restaurants.size() != 4){
            return this.restaurants.add(r);
        }
        return false;
    }

    @Override
    public double getRev() {
        double rev = 0;
        for (Restaurant r : this.restaurants){
            rev += r.getRev();
        }
        this.setSalary(rev);
        return rev;
    }


    @Override
    public void printReport() {
        System.out.println(this);
        for (Restaurant r : this.restaurants){
            r.printReportError();
        }
    }

}
