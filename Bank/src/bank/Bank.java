package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank extends ContactInfo implements IBank {

	private static final double PERCENT_OF_SALARY_TO_LEND_CREDIT = 0.5;

	private static final double PERCENT_OF_RESERVE = 0.9;

	private List<BankProduct> products = new ArrayList<BankProduct>();
	private double reserv;
	private List<Deposit> deposits = new ArrayList<>();
	private List<Credit> credits = new ArrayList<>();

	public Bank(String name, String adress, long balance, List<BankProduct> bankProducts) {
		super(name, adress, balance);
		if (bankProducts != null && bankProducts.size() > 0) {
			this.products = bankProducts;
		}

	}

	public double getRezerv() {
		return reserv;
	}

	@Override
	public Deposit openDeposit(double money) {
		this.increaseBalance(money);
		this.reserv += PERCENT_OF_RESERVE * money;
		BankProduct product;
		do {
			product = this.products.get(new Random().nextInt(products.size()));
		} while (!(product instanceof Deposit));

		Deposit result = new Deposit(product.getName(), product.getPeriod(), product.getInterest());

		result.setBalance(money);
		result.setPayedInterest(result.getBalance() * result.getInterest() / 100);

		this.deposits.add(result);

		return result;
	}

	@Override
	public Credit lendCredit(IClient client, double money, int period) {
		double sumOfVnoski = 0;

		List<Credit> credits = ((Client) client).getCredits();
		for (Credit c : credits) {
			sumOfVnoski += c.getVnoska();
		}

		if (sumOfVnoski < ((Client) client).getSalary() * PERCENT_OF_SALARY_TO_LEND_CREDIT) {
			this.decreaseBalance(money);
			this.setRezerv(PERCENT_OF_RESERVE * money);
		}

		BankProduct product;
		do {
			product = this.products.get(new Random().nextInt(products.size()));
		} while (!(product instanceof Credit));

		Credit result = new Credit(product.getName(), product.getPeriod(), product.getInterest());

		result.setBalance(money);
		result.setVnoska(money / period);
		this.credits.add(result);

		return result;
	}
	
	public StringBuilder getBankInfo() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Credits:\n");
		for (Credit c : this.credits) {
			sb.append(c.toString() + "\n");
		}
		
		sb.append("Deposits:\n");
		for (Deposit d : this.deposits) {
			sb.append(d.toString() + "\n");
		}
		
		
		return sb;
	}

	@Override
	public String toString() {
		return "Bank " + super.toString() + ", reserv=" + this.reserv;
	}

	@Override
	public void payInterests() {
		for (Deposit d : this.deposits) {
			d.increaseBalance(d.getPayedInterest());
			this.decreaseBalance(d.getPayedInterest());
			d.setPayedInterest(0);
		}

	}

	public void setRezerv(double rezerv) {
		this.reserv -= rezerv;
	}

}
