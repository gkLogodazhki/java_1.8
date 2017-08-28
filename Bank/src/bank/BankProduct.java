package bank;

public abstract class BankProduct {

	private String name;
	private int interest;
	private int period;
	private double balance;
	private static final int MAX_PERIOD = 60;
	private static final int MAX_INTEREST = 100;

	public BankProduct(String name, int period, int interest) {
		this.name = !"".equals(name) ? name : "No name";
		this.period = period > 0 && period <= MAX_PERIOD ? period : 0;
		this.interest = interest > 0 && interest <= MAX_INTEREST ? interest : 0;
	}

	public String getName() {
		return name;
	}

	public int getPeriod() {
		return period;
	}

	public double getBalance() {
		return balance;
	}

	public int getInterest() {
		return this.interest;
	}

	public void setBalance(double balance) {
		if (balance > 0) {
			this.balance = balance;
		}
	}

	protected void increaseBalance(double balance) {
		if (balance > 0) {
			this.balance += balance;
		}
	}

	@Override
	public String toString() {
		return "name=" + name + ", interest=" + interest + ", period=" + period + ", balance=" + balance;
	}

}
