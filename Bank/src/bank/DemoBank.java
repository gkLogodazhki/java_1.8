package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoBank {

	private static final int CLIENTS_COUNT = 10;
	private static final long BANK_START_BALANCE = 1_000_000L;
	private static final int CLIENT_MINIMUM_BALANCE = 1000;
	private static final int CLIENT_MAXIMUM_BALANCE = 6000;
	private static final int CLIENT_MINIMUM_SALARY = 300;
	private static final int CLIENT_MAXIMUM_SALARY = 4000;
	private static final int CREDIT_MAXIMUM_PERIOD = 60;
	private static final int SHORT_DEPOSIT_PERIOD = 3;
	private static final int SHORT_DEPOSIT_INTEREST = 3;
	private static final int LONG_DEPOSIT_PERIOD = 12;
	private static final int LONG_DEPOSIT_INTEREST = 5;
	private static final int HOME_CREDIT_INTEREST = 6;
	private static final int CONSUMER_CREDIT_INTEREST = 10;
	private static final double MIN_PERCENT_FROM_BALANCE = 80.0;
	private static final double MAX_PERCENT_FROM_BALANCE = 100;

	public static void main(String[] args) {

		Deposit shortDeposit = new Deposit("Short Deposit", SHORT_DEPOSIT_PERIOD, SHORT_DEPOSIT_INTEREST);
		Deposit longDeposit = new Deposit("Long Deposit", LONG_DEPOSIT_PERIOD, LONG_DEPOSIT_INTEREST);

		Credit homeCredit = new Credit("Home Credit", new Random().nextInt(CREDIT_MAXIMUM_PERIOD) + 1,
				HOME_CREDIT_INTEREST);
		Credit consumerCredit = new Credit("Consumer Credit", new Random().nextInt(CREDIT_MAXIMUM_PERIOD) + 1,
				CONSUMER_CREDIT_INTEREST);

		List<BankProduct> bankProducts = new ArrayList<BankProduct>();
		bankProducts.add(shortDeposit);
		bankProducts.add(longDeposit);
		bankProducts.add(homeCredit);
		bankProducts.add(consumerCredit);

		Bank bank = new Bank("BurkanBank", "Nadejda 1", BANK_START_BALANCE, bankProducts);

		final String[] names = { "Pesho", "Gesho", "Mina", "Ginka", "Penka", "Gancho", "Puhi" };

		List<Client> clients = new ArrayList<Client>();
		for (int i = 0; i < CLIENTS_COUNT; i++) {
			Client client = new Client(names[new Random().nextInt(names.length)], "Nadejda",
					new Random().nextInt(CLIENT_MAXIMUM_BALANCE - CLIENT_MINIMUM_BALANCE) + CLIENT_MINIMUM_BALANCE,
					new Random().nextInt(CLIENT_MAXIMUM_SALARY - CLIENT_MINIMUM_SALARY) + CLIENT_MINIMUM_SALARY);
			clients.add(client);
		}

		for (int i = 0; i < CLIENTS_COUNT; i++) {
			Client client = clients.get(i);
			client.openDeposit(bank, (((new Random().nextInt(21) + MIN_PERCENT_FROM_BALANCE) / MAX_PERCENT_FROM_BALANCE)
					* client.getBalance()));
		}

		System.out.println(bank.toString());

		for (int i = 0; i < CLIENTS_COUNT; i++) {
			Client client = clients.get(i);
			client.askForCredit(bank,
					(((new Random().nextInt(21) + MIN_PERCENT_FROM_BALANCE) / MAX_PERCENT_FROM_BALANCE) * client.getBalance()),
					new Random().nextInt(CREDIT_MAXIMUM_PERIOD) + 1);

		}

		// bank.payInterests();

		for (Client c : clients) {
			System.out.println(c.toString());
		}

		System.out.println(bank.toString());
		System.out.println(bank.getBankInfo());

	}

}
