package lawyer.types;

import lawyer.Jurist;

public class Prosecutor extends Jurist {

	public Prosecutor(String name, int countStaj, int countCases) {
		super(name, countStaj > 10 ? countStaj : 10, countCases);
	}

	@Override
	public String toString() {
		return "Prosecutor " + super.toString();
	}

	
}
