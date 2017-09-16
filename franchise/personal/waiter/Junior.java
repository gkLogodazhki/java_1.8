package franchise.personal.waiter;

import franchise.Order.Order;

public class Junior extends Waiter implements IWaiter {

    public Junior(String name, double salary) {
        super(name, IPersonalKind.Waiter.JUNIOR, salary ,0.02);
    }

    /*
    Ако​​служителя​​е​​младши​​сервитьор​– операцията „прехвърли на друг“ - да се намери сервитьор
    в същия ресторант от по-високо ниво и да му се даде поръчката за обслужване.
    Примерно младши сервитьор може да прехвърли на обикновен или на старши сервитьор дадена
    поръчка за изпълнение. Да се изкара съобщение на екрана, че човек с дадено име прехвърля
    поръчка с дадено заглавие на друг човек от ресторанта. Ако поемащият поръчката е
    старши сервитьор да се увеличи заплатата му с 10%.(10%)
     */
    public void giveToOther(Order order){
        Order o = this.removeOrder(order);
        this.getFranchise().giveOrderTo(this.getRestaurant(),o);
    }
}
