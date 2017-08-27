package lawyer;

import citizens.Person;

public abstract class Jurist extends Person implements IJurist  {

	private int countStaj;
	private int countCases;
	
	public Jurist(String name,int countStaj, int countCases) {
		super(name);
		this.countStaj = countStaj;
		this.countCases = countCases;
	}

	@Override
	public void askQuestion(Person person) {
		
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
	
}
