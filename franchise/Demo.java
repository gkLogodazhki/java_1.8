package franchise;

import franchise.Order.Check;
import franchise.Order.Normal;
import franchise.Order.Order;
import franchise.personal.Personal;
import franchise.personal.manager.RegionalManager;
import franchise.personal.manager.ShipManager;
import franchise.personal.waiter.Junior;
import franchise.personal.waiter.Medium;
import franchise.personal.waiter.Senior;
import franchise.personal.waiter.Waiter;

import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class Demo {

    public static void main(String[] args){

        Franchise franchise = new Franchise();
        TreeSet<Restaurant> restaurants = new TreeSet<>();

        String[] firstNames = {"Pesho", "Tosho", "Gosho", "Misho", "Niki", "Krasi","Toni","Ivan","Vasil","Borislav"};
        String[] lastNames = {"Todorov", "Petrov","Gerogiev","Nikolov","Krasimirov","Stoyanov","Dimitrov","Todorov"};

        int countRestaurant = 0;
        do{
            Restaurant r = new Restaurant("China " + countRestaurant,new Random().nextInt(10)+50);
            if(franchise.addRestaurant(r)){
                restaurants.add(r);
                countRestaurant++;
            }
        } while (restaurants.size() != 10);

        HashSet<Personal> regionalManagers = new HashSet<>();
        do{
            String name = firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
            regionalManagers.add(new RegionalManager(name,0.0));
        } while (regionalManagers.size() != 3);

        HashSet<Personal> personals = new HashSet<>();
        for (Restaurant r : restaurants){
            do{
                Personal person;
                String name = firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
                switch (franchise.getCountPersonal(r)){
                    case 0: person = (new ShipManager(name,0.0));break;
                    case 1:
                        while (true){
                            person = (Personal) regionalManagers.toArray()[new Random().nextInt(regionalManagers.size())];
                            if (((RegionalManager)person).getCountRestaurants() < 4){
                                break;
                            }
                        }
                        break;
                    default:
                        int salary = new Random().nextInt(201)+400;
                        switch (new Random().nextInt(3)){
                            case 0: person = (new Junior(name,salary)); break;
                            case 1: person = (new Medium(name,salary)); break;
                            default: person = (new Senior(name,salary)); break;
                        }
                }
                if (franchise.addPersonal(r,person)){
                    personals.add(person);
                    person.setFranchise(franchise);
                    if (person instanceof Waiter){
                        ((Waiter) person).setRestaurant(r);
                    }
                    if (person instanceof ShipManager){
                        ((ShipManager) person).addRestaurant(r);
                        person.setFranchise(franchise);
                    } else if (person instanceof RegionalManager){
                        ((RegionalManager) person).addRestaurant(r);
                    }
                }
            } while (franchise.getCountPersonal(r) != 20);

        }

        franchise.printFranchise();

        for (Restaurant r : restaurants){
            for (int i = 1; i <= 50; i++){
                Order o;
                Personal.IPersonalKind kind = Personal.IPersonalKind.Waiter.values()[new Random().nextInt(Personal.IPersonalKind.Waiter.values().length)];
                if (i <= 40){
                    o = new Normal(new Random().nextInt(50)+50, new Random().nextInt(29)+1);
                } else {
                    o = new Check(r.getName(),new Random().nextInt(50)+50, new Random().nextInt(29)+1);
                }
                r.addOrder(franchise,o,kind);
            }
        }

        for (int i = 0; i < 5; i++){
            Personal person = (Personal)personals.toArray()[new Random().nextInt(personals.size())];
            person.printReport();
        }

        Object[] restArr = restaurants.toArray();

        for (int i = 0; i < 5; i++){
            Restaurant r = (Restaurant) restArr[new Random().nextInt(restArr.length)];
            franchise.getShipManager(r).findWorstWaiter();
        }

        franchise.printFranchise();
    }
}
