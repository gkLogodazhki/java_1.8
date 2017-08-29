package singleCase.types;

import java.util.Set;

import citizens.Citizen;
import citizens.types.Defendant;
import citizens.types.ProsecutorCitizen;
import lawyer.Jurist;
import lawyer.types.Judge;
import singleCase.Case;

public class Civilian extends Case {

	private static final int COUNT_JUROR = 3;

	public Civilian(Judge judge, Defendant defendant, ProsecutorCitizen prosecutor, Set<Jurist> jurists,
			Set<Citizen> citizens) {
		super(judge, COUNT_JUROR, defendant, prosecutor, jurists, citizens);
	}

	@Override
	public String toString() {
		return "Civilian " + super.toString();
	}

}
