package lawyer.types;

import lawyer.Jurist;

public class Prosecutor extends Jurist {

	private static final int MINIMUM_COUNT_STAJ = 10;

	public Prosecutor(String name, int countStaj, int countCases) {
		super(name, countStaj > MINIMUM_COUNT_STAJ ? countStaj : MINIMUM_COUNT_STAJ, countCases);
	}

	@Override
	public String toString() {
		return "Prosecutor " + super.toString();
	}

}
