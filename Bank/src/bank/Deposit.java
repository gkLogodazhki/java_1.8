package bank;

public class Deposit extends BankProduct {

	private double payedInterest;
	
	public Deposit(String name, int period, int interest) {
		super(name, period, interest);
	}
	
	public double getPayedInterest() {
		return this.payedInterest;
	}
	
	public void setPayedInterest(double payedInterest) {
		this.payedInterest = payedInterest > 0 ? payedInterest : 0;
	}

	@Override
	public String toString() {
		return "Deposit [" + super.toString() + ", payedInterest=" + payedInterest + "]";
	}
	
	
	
}
