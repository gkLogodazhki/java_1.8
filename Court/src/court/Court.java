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

	public void addJurists(Set<Jurist> jurists) {
		this.jurists = jurists;
	}

	public void addCase(Case caseCourt) {
		this.cases.add(caseCourt);
	}
	
	public void performCases() {
		for (Case c : this.cases) {
			c.perform();
		}
	}
	
	public void resultCases() {
		for (Case c : this.cases) {
			System.out.println(c);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (Jurist j : this.jurists) {
			sb.append(j.toString() + "\n");
		}
		return "Court [name=" + name + ", address=" + address + "] , courtInfo:\n" + sb;
	}

}
