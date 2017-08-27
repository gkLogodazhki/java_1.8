package lawyer.types;

import lawyer.Jurist;

public class Lawyer extends Jurist {

	public Lawyer(String name, int countStaj, int countCases) {
		super(name, countStaj, (countCases > 10) ? countCases : 10);
	}

	@Override
	public String toString() {
		return "Lawyer " + super.toString();
	}

	
}
