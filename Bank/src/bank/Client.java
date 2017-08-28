package bank;

import java.util.ArrayList;
import java.util.List;

public class Client extends ContactInfo implements IClient{


	private static final int MIN_SUM_FOR_DEPOSIT = 0;
	private int salary;
	private List<Deposit> deposits = new ArrayList<Deposit>();
	private List<Credit> credits = new ArrayList<Credit>();

	public Client(String name, String adress, long balance, int salary) {
		super(name, adress, balance);
		if ( salary > 0 ) {
			this.salary = salary;
		}
	}

	
	@Override
	public void lenCredit(IBank bank,int index) {
		final double vnoska = this.credits.get(index).getVnoska();
		if (vnoska <= this.getBalance()) {
			this.decreaseBalance(vnoska);
			((Bank)bank).increaseBalance(vnoska);
		} else {
			System.out.println("Not enough money");
		}
	}


	@Override
	public void openDeposit(IBank bank, double money) {
		if ( money > MIN_SUM_FOR_DEPOSIT && money <= this.getBalance()) {
			this.decreaseBalance(money);
			Deposit deposit = bank.openDeposit(money);
			this.deposits.add(deposit);
		} else {
			System.out.println("Tiq pari ne stigat za otwarqne na deposit, mahaj se ot tuka");
		}
	}


	@Override
	public void askForCredit(IBank bank, double money, int period) {
		Credit credit = bank.lendCredit(this, money, period);
		credits.add(credit);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nCredits:\n");
		for (Credit c : this.credits) {
			sb.append(c.toString() + "\n");
		}
		sb.append("Deposits:\n");
		for (Deposit d : this.deposits) {
			sb.append(d.toString() + "\n");
		}
		return "Client " + super.toString() + sb ;
	}
	
	public List<Credit> getCredits() {
		return credits;
	}
	
	public int getSalary() {
		return salary;
	}


	public double getDepositMoney () {
		double money = 0;
		for (Deposit d:deposits) {
			money += d.getBalance();
		}
		return money;
	}

}
