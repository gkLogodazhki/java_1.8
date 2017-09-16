package franchise.personal.waiter;

public class Senior extends Waiter implements IWaiter {

    public Senior(String name, double salary) {
        super(name, IPersonalKind.Waiter.SENIOR, salary,0.06);
    }

}
