package citizens;

public class Person {

	private String name;
	
	public Person(String name) {
		this.name = (!"".equals(name)) ? name : "No name";
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "[name=" + name ;
	}
	
}
