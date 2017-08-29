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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (this.getName() == null) {
			if (other.getName()!= null)
				return false;
		} else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}
		
}
