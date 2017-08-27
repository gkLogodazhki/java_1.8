package citizens;

public class Citizen extends Person {


	private String address;
	private int age;
	
	public Citizen(String name,String address, int age) {
		super(name);
		this.address = (!"".equals(address)) ? address : "No address";
		this.age = (age > 16) ? age : 16;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", address=" + address + ", age=" + age + "] ";
	}
	
	
}
