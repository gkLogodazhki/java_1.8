package franchise.personal.waiter;

public class Medium extends Waiter implements IWaiter {

    public Medium(String name, double salary) {
        super(name, IPersonalKind.Waiter.MEDIUM, salary,0.04);
    }

}
