package bank;

public interface IBank {
	
	Deposit openDeposit(double money);
	Credit lendCredit(IClient client, double money, int period);
	void payInterests();

}
