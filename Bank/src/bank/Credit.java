package bank;

public class Credit extends BankProduct{

	private double vnoska;
	
	public Credit(String name, int period, int interest) {
		super(name, period, interest);
	}
	
	public double getVnoska() {
		return vnoska;
	}
	
	public void setVnoska(double vnoska) {
		this.vnoska = vnoska > 0 ? vnoska : 0;
	}

	@Override
	public String toString() {
		return "Credit [" + super.toString() +", vnoska=" + vnoska + "]";
	}
	
	

}
