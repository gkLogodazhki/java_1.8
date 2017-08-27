package singleCase.types;

import java.util.Set;

import citizens.Citizen;
import citizens.types.Defendant;
import lawyer.Jurist;
import lawyer.types.Judge;
import lawyer.types.Prosecutor;
import singleCase.Case;

public class CriminalProceedings extends Case {

	public static final int COUNT_JURORS = 13;
	public CriminalProceedings(Judge judge,Defendant defendant,Prosecutor prosecutor,Set<Jurist> jurists,Set<Citizen> citizens) {
		super(judge, COUNT_JURORS,defendant,prosecutor,jurists,citizens);
	}

	@Override
	public String toString() {
		return "CriminalProceedings " + super.toString();
	}

}
