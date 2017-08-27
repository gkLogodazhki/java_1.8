package singleCase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import citizens.Citizen;
import citizens.Person;
import citizens.types.Defendant;
import citizens.types.Witness;
import lawyer.Jurist;
import lawyer.types.Judge;
import lawyer.types.Juror;
import lawyer.types.Lawyer;
import lawyer.types.Prosecutor;
import singleCase.types.Civilian;
import singleCase.types.CriminalProceedings;

public abstract class Case {

	private Judge judge;
	private Set<Juror> jurors;
	private int countJurors;
	private Defendant defendant;
	private Person prosecutor;
	private Set<Witness> witness;
	private StringBuilder chronology;
	
	public void addJuror(Juror juror) {
		this.jurors.add(juror);
	}
	
	public Case(Judge judge,int countJurors,Defendant defendant, Person prosecutor,Set<Jurist> jurists,Set<Citizen> citizen) {
		this.judge = judge;
		this.defendant = defendant;
		this.prosecutor = prosecutor;
		this.countJurors = (countJurors > 0) ? countJurors : 0;
		
		this.jurors = new HashSet<Juror>();
		Object[] jurorsArr = jurists.toArray();
		do {
			int rand = new Random().nextInt(jurists.size());
			if (jurorsArr[rand] instanceof Juror) {
				this.addJuror((Juror) jurorsArr[rand]);
			}
		} while (this.getJurors().size() != this.countJurors);
		
		this.witness = new HashSet<Witness>();
		Object[] witnessArr = citizen.toArray();
		int countWitness = new Random().nextInt(5);
		do {
			int rand = new Random().nextInt(citizen.size());
			if (witnessArr[rand] instanceof Witness) {
				this.witness.add((Witness) witnessArr[rand]);
			}
		} while (this.witness.size() < countWitness);
		
		this.chronology = new StringBuilder("");
	
	}
	public Set<Juror> getJurors() {
		return this.jurors;
	}
	
	public void perform () {
		
		this.judge.increaseCountCases();
		this.chronology.append(this.judge.toString() + "\n");
		
		if (this instanceof CriminalProceedings) {
			((Prosecutor)this.prosecutor).increaseCountCases();
		}
		this.chronology.append(this.prosecutor.toString() + "\n");

		
		for (Juror j : this.jurors) {
			j.increaseCountCases();
			this.chronology.append(j.toString() + "\n");
		}
		
		for (Witness w : this.witness) {
			this.chronology.append(w.toString() + "\n");
		}
		
		if (this instanceof Civilian) {
			for (Lawyer l : this.defendant.getLawyers()) {
				this.askQuestionTo(l, this.defendant, 3);
				this.askQuestionToWitness(l, 2);
			}
		} else {
			this.askQuestionTo(((Prosecutor)this.prosecutor), this.defendant, 5);
			
			this.askQuestionToWitness(((Prosecutor)this.prosecutor), 5);
		}
		
		for (Lawyer l : this.defendant.getLawyers()) {
			if (this instanceof CriminalProceedings) {
				this.askQuestionTo(((Prosecutor)this.prosecutor),l,5);
			}
		}
		
		boolean isGuilty = decision();
		
		this.chronology.append("Juror decision: " + (isGuilty ? "Guilty" : "Not Guilty") + "\n");
		
		if (isGuilty) {
			this.chronology.append("The defendant must go for " + (new Random().nextInt(38) + 3) + " in jail\n");
		}
		
	}

	private void askQuestionTo(Jurist jurist,Person citizen,int countQuestions) {
		for (int i = 0; i < countQuestions; i++) {
			jurist.askQuestion(citizen);
			this.chronology.append(jurist.toString() + " ask question to " + citizen.toString() + "\n");
		}
	}
	
	private void askQuestionToWitness(Jurist jurist,int countQuestions) {
		for (Witness w: this.witness) {
			this.askQuestionTo(jurist,w,countQuestions);
		}
	}
	
	public boolean decision() {
		int count = 0;
		for(Juror j: this.jurors) {
			if (new Random().nextBoolean()) {
				this.chronology.append(j.toString() + " decision is guilty\n");
				count++;
			} else {
				this.chronology.append(j.toString() + " decision is not guilty\n");
			}
		}
		return (count > this.countJurors/2) ? true : false;
	}

	@Override
	public String toString() {
		return "Case chronology: \n" + chronology;
	}
	
	
}
