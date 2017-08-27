package lawyer.types;

import lawyer.Jurist;

public class Juror extends Jurist {

	public Juror(String name, int countStaj, int countCases) {
		super(name, countStaj, countCases);
	}

	@Override
	public String toString() {
		return "Juror " + super.toString();
	}

	
}
