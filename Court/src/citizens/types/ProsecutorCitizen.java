package citizens.types;

import java.util.Set;

import lawyer.types.Lawyer;

public class ProsecutorCitizen extends DefendentAndProsecutor {

	public ProsecutorCitizen(String name, String address, int age, Set<Lawyer> lawyers ) {
		super(name, address, age,lawyers);
	}

	@Override
	public String toString() {
		return "ProsecutorCitizen " + super.toString();
	}

}
