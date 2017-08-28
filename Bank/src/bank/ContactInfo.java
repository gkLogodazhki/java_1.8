package bank;

public abstract class ContactInfo {

	private String name;
	private String adress;
	private long balance;

	public ContactInfo(String name, String address, long balance) {
		this.name = !"".equals(name) ? name : "No name";
		this.adress = !"".equals(address) ? address : "No address";
		this.balance = balance > 0 ? balance : 0;
	}

	long getBalance() {
		return this.balance;
	}

	void decreaseBalance(double money) {
		if (money > 0 && money <= this.balance) {
			this.balance -= money;
		}
	}

	void increaseBalance(double money) {
		if (money > 0) {
			this.balance += money;
		}
	}

	public String toString() {
		return "[name=" + this.name + ", address=" + this.adress + ", balance=" + balance + "]";

	}

}
