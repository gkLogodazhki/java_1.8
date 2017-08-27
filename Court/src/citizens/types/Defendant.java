package citizens.types;

import java.util.Set;

import lawyer.types.Lawyer;

public class Defendant extends DefendentAndProsecutor {

	public Defendant(String name, String address, int age,Set<Lawyer> lawyers) {
		super(name, address, age,lawyers);
	}

	@Override
	public String toString() {
		return "Defendant " + super.toString();
	}	
	
	
}
