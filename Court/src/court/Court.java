package court;

import java.util.HashSet;
import java.util.Set;

import lawyer.Jurist;
import singleCase.Case;

public class Court {

	private String name;
	private String address;
	private Set<Jurist> jurists;
	private Set<Case> cases;

	public Court(String name) {
		this.name = (!"".equals(name)) ? name : "No name";
		this.jurists = new HashSet<>();
		this.cases = new HashSet<>();
	}

	public void addJurist(Jurist jurist) {
		this.jurists.add(jurist);
	}

	public void addCase(Case caseCourt) {
		this.cases.add(caseCourt);
	}

	public Set<Jurist> getJurists() {
		return this.jurists;
	}

	public Set<Case> getCases() {
		return this.cases;
	}

	private StringBuilder printCourtInfo() {
		StringBuilder sb = new StringBuilder("");
		for (Jurist j : this.jurists) {
			sb.append(j.toString() + "\n");
		}
		return sb;
	}

	@Override
	public String toString() {
		return "Court [name=" + name + ", address=" + address + "] , courtInfo:\n" + this.printCourtInfo();
	}

}
