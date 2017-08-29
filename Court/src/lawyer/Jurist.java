package lawyer;

import citizens.Person;

public abstract class Jurist extends Person implements IJurist {

	private int countStaj;
	private int countCases;

	public Jurist(String name, int countStaj, int countCases) {
		super(name);
		this.countStaj = countStaj;
		this.countCases = countCases;
	}

	@Override
	public String askQuestion(Person person) {
		return this + " ask question to " + person;
	}

	@Override
	public void proceedings() {
		// TODO Auto-generated method stub

	}

	public void increaseCountCases() {
		this.countCases++;
	}

	@Override
	public String toString() {
		return super.toString() + ", countStaj=" + countStaj + ", countCases=" + countCases + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result += prime * result + countCases;
		result += prime * result + countStaj;
		result += prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
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
		Jurist other = (Jurist) obj;
		if (countCases != other.countCases)
			return false;
		if (countStaj != other.countStaj)
			return false;
		if (this.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}

}
