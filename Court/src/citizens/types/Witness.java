 package citizens.types;

import citizens.Citizen;

public class Witness extends Citizen {

	public Witness(String name, String address, int age) {
		super(name, address, age);
	}

	@Override
	public String toString() {
		return "Witness "+ super.toString();
	}

	
}
