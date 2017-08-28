package bank;

public interface IClient {
	
	void openDeposit(IBank bank, double money);
	void askForCredit(IBank bank, double money, int period);
	void lenCredit(IBank bank,int index); 
	
}
