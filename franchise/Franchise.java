package franchise;

import franchise.Order.Order;
import franchise.personal.Personal;
import franchise.personal.Personal.*;

import java.util.*;

import franchise.personal.Personal.IPersonalKind;
import franchise.personal.manager.ShipManager;
import franchise.personal.waiter.Waiter;

import javax.swing.text.html.HTMLDocument;

public class Franchise {

    private TreeMap<Restaurant, HashMap<PersonalType,HashMap<IPersonalKind,HashSet<Personal>>>> franchise;

    public Map getFranchise(){
        return Collections.unmodifiableMap(franchise);
    }

    public Franchise(){
        this.franchise = new TreeMap<>();
    }

    public void printFranchise(){
        for (Map.Entry<Restaurant, HashMap<PersonalType,HashMap<IPersonalKind,HashSet<Personal>>>> entryRestaurant : this.franchise.entrySet()){
            System.out.println(entryRestaurant.getKey());
            for (Map.Entry<PersonalType,HashMap<IPersonalKind,HashSet<Personal>>> entryPersonalType : entryRestaurant.getValue().entrySet()){
                System.out.println("   " + entryPersonalType.getKey());
                for (Map.Entry<IPersonalKind,HashSet<Personal>> entryIPersonalKind : entryPersonalType.getValue().entrySet()){
                    System.out.println("      " + entryIPersonalKind.getKey());
                    for (Personal p : entryIPersonalKind.getValue()){
                        System.out.println("         " + p);
                    }
                }
            }
        }
    }

    public boolean addRestaurant(Restaurant r){
        if (!this.franchise.containsKey(r)){
            this.franchise.put(r, new HashMap<>());
            return true;
        }
        return false;
    }

    /*
    Да​​се​​назначи​​служител​на дадена позиция в даден ресторант(по име на ресторанта).
    Ако ресторант с такова име не съществува все още, то първия назначен задължително
    трябва да е мениджър-смяна. (10%)
     */
    public boolean addPersonal(Restaurant r,Personal p){

        if (!this.franchise.get(r).containsKey(p.getType())){
            this.franchise.get(r).put(p.getType(), new HashMap<>());
        }
        if (!this.franchise.get(r).get(p.getType()).containsKey(p.getKind())){
            this.franchise.get(r).get(p.getType()).put(p.getKind(), new HashSet<>());
        }

        return this.franchise.get(r).get(p.getType()).get(p.getKind()).add(p);

    }

    /*
    Да​​се​​обработи​​заплатите.​Фирмата трябва да раздаде заплати на служителите.
    Ако служителят е сервитьор тогава заплатата му се начислява по следната формула -
    базова заплата между 400 и 600 лв + процент от собствения оборот на сервитьора,
    като процентите са както следва според ранга на сервитьора (2%, 4% и 6%). Да се
    има в предвид че ако сервитьора има оплаквания се санкционира с 20 лв за всяко
    едно от тях. Ако служителят е мениджър на смяна заплатата му е 4% от оборота на
    заведението, като той също търпи санкции за обърканите поръчки на сервитьорите
    (по 10 лв на объркана поръчка). Ако е регионален мениджър получава 6% от оборота
    на заведенията, които ръководи. На конзолата да се изкара информация на кого,
    каква заплата е дадена и накрая - колко пари сумарно отиват за заплати. (10%)
     */
    public void paySalary(){
        double totalSalary = 0;
        for (Map.Entry<Restaurant, HashMap<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>>> entryRestaurant : this.franchise.entrySet()){
            System.out.println(entryRestaurant.getKey());
            for (Map.Entry<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>> entryPersonalType : entryRestaurant.getValue().entrySet()){
                System.out.println("   " + entryPersonalType.getKey());
                for (Map.Entry<IPersonalKind,HashSet<Personal>> entryIPersonalKind : entryPersonalType.getValue().entrySet()){
                    System.out.println("      " + entryIPersonalKind.getKey());
                    for (Personal p : entryIPersonalKind.getValue()){
                        double salary = p.getSalary();
                        System.out.println("         " + p + " take salary: " + salary);
                        totalSalary += salary;
                    }
                }
            }
        }
        System.out.println("Total money for salary " + totalSalary);
    }

    //Да​​ се​ ​изведат ​​данни ​за всички обработени поръчки – заглавие
    // и сложност, сортирани по сложност. (5%)
    public void allOrders(){
        TreeSet<Order> orders = new TreeSet<>((o1, o2) -> o2.getCountClients()-o1.getCountClients());
        for (Map.Entry<Restaurant, HashMap<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>>> entryRestaurant : this.franchise.entrySet()){
            orders.addAll(entryRestaurant.getKey().getOrders().values());
        }
        for (Order o : orders){
            System.out.println(o);
        }
    }

    //Операция​​ служител ​​на ​​месеца​- да се изведе сервитьора
    // с най-малко объркани поръчки. (5%)
    public void theBestWaiter(){
        TreeSet<Waiter> waiters = new TreeSet<>((o1, o2) -> o1.getErrors()-o2.getErrors());
        for (Map.Entry<Restaurant, HashMap<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>>> entryRestaurant : this.franchise.entrySet()){
            for (Map.Entry<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>> entryPersonalType : entryRestaurant.getValue().entrySet()){
                if (entryPersonalType.getKey().equals(PersonalType.WAITER)){
                    for (Map.Entry<IPersonalKind,HashSet<Personal>> entryIPersonalKind : entryPersonalType.getValue().entrySet()){
                        for (Personal p : entryIPersonalKind.getValue()){
                            waiters.add((Waiter)p);
                        }
                    }
                }
            }
        }
        System.out.println("The best waiter is " + waiters.first());

        //Да ​​се ​​изведе ​​името ​​на ​​сервитьора​, за който сумарно
        // има най-много объркани поръчки. (5%)

        System.out.println("The worst waiter is " + waiters.last().getName());

    }

    public void decreaseManagerMoney(Restaurant r,int money) {
        this.franchise.get(r).get(PersonalType.MANAGER).get(IPersonalKind.Manager.SHIP_MANAGER).iterator().next().decreaseSalary(money);
    }

    public void topThreeRestaurants(){
        TreeMap<Restaurant,Integer> restaurant = new TreeMap<>((o1, o2) -> o1.getCountErrors()-o2.getCountErrors());
        for (Restaurant r : this.franchise.keySet()){
            restaurant.put(r,r.getCountErrors());
        }

        int count = 0;
        for (Restaurant r : restaurant.keySet()){
            System.out.println(r);
            for (Map.Entry<Personal.PersonalType,HashMap<IPersonalKind,HashSet<Personal>>> entryPersonalType : this.franchise.get(r).entrySet()){
                for (Map.Entry<IPersonalKind,HashSet<Personal>> entryIPersonalKind : entryPersonalType.getValue().entrySet()){
                    for (Personal p : entryIPersonalKind.getValue()){
                        p.increaseSalary(0.1);
                    }
                }
            }
            if (count++ == 3) break;
        }
    }

    public void giveOrderTo(Restaurant r,Order order){
        IPersonalKind kind = IPersonalKind.Waiter.values()[new Random().nextInt(IPersonalKind.Waiter.values().length)];
        do{
            kind = IPersonalKind.Waiter.values()[new Random().nextInt(IPersonalKind.Waiter.values().length)];
        } while (kind.equals(IPersonalKind.Waiter.JUNIOR));
        Waiter waiter = (Waiter) this.franchise.get(r).get(PersonalType.WAITER).get(kind).toArray()[new Random().nextInt(this.franchise.get(r).get(PersonalType.WAITER).get(kind).size())];
        waiter.takeOrder(order);
        if (kind.equals(IPersonalKind.Waiter.SENIOR)){
            waiter.increaseSalary(10);
        }
    }

    public Waiter getTheWorstWaiter(Restaurant r){
        TreeSet<Waiter> w = new TreeSet<>((o1, o2) -> o2.getErrors()-o1.getErrors());
        for (Map.Entry<IPersonalKind,HashSet<Personal>> entry : this.franchise.get(r).get(PersonalType.WAITER).entrySet()){
            for (Personal p : entry.getValue()){
                w.add((Waiter) p);
            }
        }
        return w.iterator().next();
    }

    public void fireHim(Restaurant r,Waiter w) {
        for (Map.Entry<IPersonalKind,HashSet<Personal>> entry : this.franchise.get(r).get(PersonalType.WAITER).entrySet()){
            if (entry.getValue().contains(w)){
                entry.getValue().remove(w);
                break;
            }
        }
    }

    public int getCountPersonal(Restaurant r){
        int count = 0;
        for (Map.Entry<PersonalType,HashMap<IPersonalKind,HashSet<Personal>>> entryPersonalType : this.franchise.get(r).entrySet()){
            for (Map.Entry<IPersonalKind,HashSet<Personal>> entryPersonalKind : entryPersonalType.getValue().entrySet()){
                count += entryPersonalKind.getValue().size();
            }
        }
        return count;
    }

    public int getCountTotalErrors(){
        int errors = 0;
        for (Restaurant r : this.franchise.keySet()){
            errors += r.getCountErrors();
        }
        return errors;
    }

    public Waiter getWaiter(Restaurant r, IPersonalKind personalKind) {
        PersonalType personalType = PersonalType.WAITER;
        Object[] waiters = this.franchise.get(r).get(personalType).get(personalKind).toArray();
        Waiter waiter = (Waiter) waiters[new Random().nextInt(waiters.length)];
        return waiter;
    }

    public ShipManager getShipManager(Restaurant r) {
        return (ShipManager) this.franchise.get(r).get(PersonalType.MANAGER).get(IPersonalKind.Manager.SHIP_MANAGER).iterator().next();
    }

}
