package court;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import citizens.Citizen;
import citizens.types.Defendant;
import citizens.types.ProsecutorCitizen;
import citizens.types.Witness;
import lawyer.Jurist;
import lawyer.types.Judge;
import lawyer.types.Juror;
import lawyer.types.Lawyer;
import lawyer.types.Prosecutor;
import singleCase.types.Civilian;
import singleCase.types.CriminalProceedings;

public class Demo {

	private static final int COUNT_OF_JUDGES = 3;
	private static final int COUNT_OF_JURISTS = 20;
	private static final int COUNT_OF_LAWYERS = 5;
	private static final int COUNT_OF_PROSECUTORS = 2;
	private static final int COUNT_OF_DEFENDANT = 5;
	private static final int COUNT_OF_PROSECUTORS_CITIZEN = 5;
	private static final int COUNT_OF_WITNESSES = 10;
	private static final int COUNT_OF_CIVILIAN_CASES = 3;
	private static final int COUNT_OF_CRIMINAL_PROCEEDINGS = 3;
	private static final int MINIMUM_YEARS = 18;
	private static final int MAXIMUM_YEARS = 68;
	private static final int NUMBER = 20;

	public static void main(String[] args) {

		Court court = new Court("Veliko Tyrnovo");

		String[] names = { "Pesho", "Gancho", "Gosho", "Tosho", "Ivan", "Slavi", "Nikolai", "Asen", "Boris" };
		String[] secondNames = { "Kirilov", "Ivanov", "Nikolov" };
		String[] lastNames = { "Dimitrov", "Popov", "Borisov" };

		Set<Jurist> jurists = new HashSet<>();

		do {
			jurists.add(new Judge(names[new Random().nextInt(names.length)], new Random().nextInt(NUMBER),
					new Random().nextInt(NUMBER)));
		} while (jurists.size() != COUNT_OF_JUDGES);

		do {
			jurists.add(new Juror(names[new Random().nextInt(names.length)], new Random().nextInt(NUMBER),
					new Random().nextInt(NUMBER)));
		} while (jurists.size() != COUNT_OF_JUDGES + COUNT_OF_JURISTS);

		Set<Lawyer> lawyersDefender = new HashSet<>();
		Set<Lawyer> lawyersProsecutor = new HashSet<>();
		
		int rand = new Random().nextInt(COUNT_OF_LAWYERS-1)+1;
		int index = 0;
		do {
			Lawyer lawyer = new Lawyer(names[new Random().nextInt(names.length)], new Random().nextInt(NUMBER),
					new Random().nextInt(NUMBER));
			jurists.add(lawyer);
			if (index < rand) {
				lawyersDefender.add(lawyer);
				index++;
			} else {
				lawyersProsecutor.add(lawyer);
			}
		} while (jurists.size() != COUNT_OF_JUDGES + COUNT_OF_JURISTS + COUNT_OF_LAWYERS);

		do {
			jurists.add(new Prosecutor(names[new Random().nextInt(names.length)], new Random().nextInt(NUMBER),
					new Random().nextInt(NUMBER)));
		} while (jurists.size() != COUNT_OF_JUDGES + COUNT_OF_JURISTS + COUNT_OF_LAWYERS + COUNT_OF_PROSECUTORS);

		Set<Citizen> citizens = new HashSet<>();

		for (int i = 0; i < COUNT_OF_DEFENDANT; i++) {
			citizens.add(new Defendant(
					names[new Random().nextInt(names.length)] + " "
							+ secondNames[new Random().nextInt(secondNames.length)] + " "
							+ lastNames[new Random().nextInt(secondNames.length)],
					"Varna", new Random().nextInt(MAXIMUM_YEARS - MINIMUM_YEARS) + MINIMUM_YEARS, lawyersDefender));
		}
		for (int i = 0; i < COUNT_OF_PROSECUTORS_CITIZEN; i++) {
			citizens.add(new ProsecutorCitizen(
					names[new Random().nextInt(names.length)] + " "
							+ secondNames[new Random().nextInt(secondNames.length)] + " "
							+ lastNames[new Random().nextInt(secondNames.length)],
					"Varna", new Random().nextInt(MAXIMUM_YEARS - MINIMUM_YEARS) + MINIMUM_YEARS, lawyersProsecutor));
		}
		for (int i = 0; i < COUNT_OF_WITNESSES; i++) {
			citizens.add(new Witness(
					names[new Random().nextInt(names.length)] + " "
							+ secondNames[new Random().nextInt(secondNames.length)] + " "
							+ lastNames[new Random().nextInt(secondNames.length)],
					"Varna", new Random().nextInt(MAXIMUM_YEARS - MINIMUM_YEARS) + MINIMUM_YEARS));
		}

		Object[] juristsArr = jurists.toArray();
		Object[] citizensArr = citizens.toArray();
		court.addJurists(jurists);


		for (int i = 0; i < COUNT_OF_CIVILIAN_CASES; i++) {
			Judge judge = new GetPerson<Judge>().get(juristsArr,new Judge(null,0,0));
			Defendant defendant = new GetPerson<Defendant>().get(citizensArr,new Defendant(null, null, 0, null));
			ProsecutorCitizen prosecutor = new GetPerson<ProsecutorCitizen>().get(citizensArr,new ProsecutorCitizen(null, null, 0, null));
			defendant.setLawyers(lawyersDefender);
			prosecutor.setLawyers(lawyersProsecutor);
			court.addCase(new Civilian(judge, defendant, prosecutor, jurists, citizens));
		}

		for (int i = 0; i < COUNT_OF_CRIMINAL_PROCEEDINGS; i++) {
			Judge judge = new GetPerson<Judge>().get(juristsArr,new Judge(null,0,0));
			Defendant defendant = new GetPerson<Defendant>().get(citizensArr,new Defendant(null, null, 0, null));
			Prosecutor prosecutor = new GetPerson<Prosecutor>().get(juristsArr,new Prosecutor(null,0,0));
			defendant.setLawyers(lawyersDefender);
			court.addCase(new CriminalProceedings(judge, defendant, prosecutor, jurists, citizens));
		}

		System.out.println(court);
		court.performCases();
		court.resultCases();
		System.out.println(court.toString());

	}

}
