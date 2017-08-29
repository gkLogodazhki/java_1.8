package citizens.types;

import java.util.Set;

import citizens.Citizen;
import lawyer.types.Lawyer;

public abstract class DefendentAndProsecutor extends Citizen {

	private Set<Lawyer> lawyers;

	public DefendentAndProsecutor(String name, String address, int age, Set<Lawyer> lawyers) {
		super(name, address, age);
		this.lawyers = lawyers;
	}

	public Set<Lawyer> getLawyers() {
		return this.lawyers;
	}
	
	public void setLawyers(Set<Lawyer> lawyers) {
		this.lawyers = lawyers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Lawyer l : this.lawyers) {
			sb.append("\n" + l.toString());
		}
		return super.toString() + sb;
	}

}
