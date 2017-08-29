package lawyer.types;

import lawyer.Jurist;

public class Judge extends Jurist {
	
	private static final int MINIMUM_COUNT_STAJ = 5;
	
	public Judge(String name, int countStaj, int countCases) {
		super(name, (countStaj > MINIMUM_COUNT_STAJ) ? countStaj : MINIMUM_COUNT_STAJ, countCases);
	}

	@Override
	public String toString() {
		return "Judge " + super.toString();
	}

}
