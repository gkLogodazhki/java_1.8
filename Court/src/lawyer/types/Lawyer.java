package lawyer.types;

import lawyer.Jurist;

public class Lawyer extends Jurist {

	private static final int MINIMUM_COUNT_CASES = 10;

	public Lawyer(String name, int countStaj, int countCases) {
		super(name, countStaj, countCases > MINIMUM_COUNT_CASES ? countCases : MINIMUM_COUNT_CASES);
	}

	@Override
	public String toString() {
		return "Lawyer " + super.toString();
	}

}
