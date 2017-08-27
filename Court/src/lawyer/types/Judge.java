 package lawyer.types;

import lawyer.Jurist;

public class Judge extends Jurist {

	public Judge(String name, int countStaj, int countCases) {
		super(name, (countStaj > 5 ) ? countStaj : 5, countCases);
	}
	
	@Override
	public String toString() {
		return "Judge " + super.toString();
	}

	
	
}
