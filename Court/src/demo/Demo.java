package demo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import citizens.Citizen;
import citizens.Person;
import citizens.types.Defendant;
import citizens.types.ProsecutorCitizen;
import citizens.types.Witness;
import court.Court;
import lawyer.Jurist;
import lawyer.types.Judge;
import lawyer.types.Juror;
import lawyer.types.Lawyer;
import lawyer.types.Prosecutor;
import singleCase.Case;
import singleCase.types.Civilian;
import singleCase.types.CriminalProceedings;

public class Demo {

	public static void main(String[] args) {
		
		
		Court court = new Court("Veliko Tyrnovo");
		
		String[] names = {"Pesho","Gancho","Gosho","Tosho","Ivan","Slavi","Nikolai","Asen","Boris"};
		String[] secondNames = {"Kirilov","Ivanov","Nikolov"};
		String[] lastNames = {"Dimitrov", "Popov", "Borisov"};
		do {
			court.addJurist(new Judge(names[new Random().nextInt(names.length)], new Random().nextInt(20), new Random().nextInt(20) ));
		} while (court.getJurists().size() != 3);
		
		do {
			court.addJurist(new Juror(names[new Random().nextInt(names.length)], new Random().nextInt(20), new Random().nextInt(20) ));
		} while (court.getJurists().size() != 23);
		
		
		Set<Lawyer> lawyersDefender = new HashSet<>();
		Set<Lawyer> lawyersProsecutor = new HashSet<>();
		
		do {
			Lawyer lawyer = new Lawyer(names[new Random().nextInt(names.length)], new Random().nextInt(20), new Random().nextInt(20) );
			court.addJurist(lawyer);
			if (new Random().nextBoolean()) {
				lawyersDefender.add(lawyer);
			} else {
				lawyersProsecutor.add(lawyer);
			}
		} while (court.getJurists().size() != 28);
		
		do {
			court.addJurist(new Prosecutor(names[new Random().nextInt(names.length)], new Random().nextInt(20), new Random().nextInt(20) ));
		} while (court.getJurists().size() != 30);
		
		Set<Citizen> citizens = new HashSet<>();
		
	
		for (int i = 0; i < 5; i++) {
			citizens.add(new Defendant(names[new Random().nextInt(names.length)] + " " +secondNames[new Random().nextInt(secondNames.length)] + " " + lastNames[new Random().nextInt(secondNames.length)], "Varna", new Random().nextInt(50)+18,lawyersDefender));
		}
		for (int i = 0; i < 5; i++) {
			citizens.add(new ProsecutorCitizen(names[new Random().nextInt(names.length)] + " " +secondNames[new Random().nextInt(secondNames.length)] + " " + lastNames[new Random().nextInt(secondNames.length)], "Varna", new Random().nextInt(50)+18,lawyersProsecutor));
		}
		for (int i = 0; i < 10; i++) {
			citizens.add(new Witness(names[new Random().nextInt(names.length)] + " " +secondNames[new Random().nextInt(secondNames.length)] + " " + lastNames[new Random().nextInt(secondNames.length)], "Varna", new Random().nextInt(50)+18));
		}
				
		Object[] jurists = court.getJurists().toArray();
		Object[] citizensArr = citizens.toArray();
		
		
		for (int i = 0; i < 3; i++) {
			Judge judge;
			Defendant defendant;
			ProsecutorCitizen prosecutor;
			while(true) {
				int rand = new Random().nextInt(jurists.length);
				if (jurists[rand] instanceof Judge ) {
					judge = (Judge) jurists[rand];
					break;
				}
			}
			while (true) {
				int rand = new Random().nextInt(citizensArr.length);
				if (citizensArr[rand] instanceof Defendant) {
					defendant = (Defendant) citizensArr[rand];
					break;
				}
			}
			while (true) {
				int rand = new Random().nextInt(citizensArr.length);
				if (citizensArr[rand] instanceof ProsecutorCitizen) {
					prosecutor = (ProsecutorCitizen) citizensArr[rand];
					break;
				}
			}
			court.addCase(new Civilian(judge, defendant, prosecutor,court.getJurists(),citizens));
		}
		
		for (int i = 0; i < 3; i++) {
			Judge judge;
			Defendant defendant;
			Prosecutor prosecutor;
			while(true) {
				int rand = new Random().nextInt(jurists.length);
				if (jurists[rand] instanceof Judge ) {
					judge = (Judge) jurists[rand];
					break;
				}
			}
			while (true) {
				int rand = new Random().nextInt(citizensArr.length);
				if (citizensArr[rand] instanceof Defendant) {
					defendant = (Defendant) citizensArr[rand];
					break;
				}
			}
			while (true) {
				int rand = new Random().nextInt(jurists.length);
				if (jurists[rand] instanceof Prosecutor) {
					prosecutor = (Prosecutor) jurists[rand];
					break;
				}
			}
			court.addCase(new CriminalProceedings(judge, defendant, prosecutor,court.getJurists(),citizens));
		}
		
		System.out.println(court.toString());

		for (Case c : court.getCases()) {
			c.perform();
			System.out.println(c.toString());
		}
		
		System.out.println(court.toString());

	}

}
