package interfaces;

import enums.Date;
import person.Driver;
import vehicles.Vehicle;

public interface IPetrol {

	void generateVignettes(int count);
	boolean sellVignette(Driver driver,Vehicle vehicle, Date duration);
	void printVignettes();
	
}
