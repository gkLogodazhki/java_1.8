package petrolStations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import enums.Date;
import enums.EnumVehicle;
import person.Driver;
import person.IDriver;
import vehicles.Vehicle;

public class Demo {
	
	private static final int PETROL_STATION_START_BALANCE = 1000;
	private static final int COUNT_OF_DRIVERS = 20;
	private static final int COUNT_OF_VEHICLES = 200;
	private static final int COUNT_OF_VEHICLES_PER_DRIVER = 10;
	private static final LocalDate CHECK_DATE = LocalDate.parse("2018-01-01");
	private static final int YEAR_FROM = 1960;
	private static final int YEAR_TO = 2017;
	private static final int DRIVER_MINIMUM_MONEY = 1000;
	private static final int DRIVER_RANDOM_EXTRA_MONEY = 1500;
	private static final String[] NAMES = {"Pesho","Tosho","Gosho","Misho","Niki","Puhi","Ivan","Slavi","Toni"};
	private static final String[] MODELS = {"BMW","Mercedes","Opel","VW","Fiat","Audi","Peugeot"};

	public static void main(String[] args) {
		
		IPetrol petrol;
		petrol = new Petrol("NaGosho",PETROL_STATION_START_BALANCE);
		
		List<IDriver> drivers = new ArrayList<IDriver>(COUNT_OF_DRIVERS);
		listDrivers(drivers, petrol);
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>(COUNT_OF_VEHICLES);
		listVehicles(vehicles);
		
		setDriversVehicles(drivers, vehicles);
		
		buyVignettes(drivers);
		
		printDriversInfo(drivers, CHECK_DATE);
		
//		petrol.printVignettes();
		
		printVehicleTypeVignetteStatus(vehicles, EnumVehicle.TRUCK, CHECK_DATE);
		
	}
	private static void printVehicleTypeVignetteStatus(List<Vehicle> vehicles, EnumVehicle vehicleType, LocalDate date) {
		int count = 0;
		System.out.println();
		for (Vehicle v : vehicles) {

			if ((v.getVignette() == null && v.getVehicleType().equals(vehicleType)) || v.getVehicleType().equals(vehicleType) && (v.getVignette().getSoldDate().toEpochDay() + v.getVignette().getDuration().getDays() < date.toEpochDay())) {

				System.out.println(v.toString());
				count++;
			}
		}
		System.out.println("Total count: " + count);
	}
	
	private static void listDrivers(List<IDriver> drivers, IPetrol petrol) {
		for (int i = 0; i < COUNT_OF_DRIVERS; i++) {
			int rand = new Random().nextInt(NAMES.length);
			drivers.add(new Driver(NAMES[rand],new Random().nextInt(DRIVER_RANDOM_EXTRA_MONEY) + DRIVER_MINIMUM_MONEY,petrol));
		}
	}
	
	private static void listVehicles(List<Vehicle> vehicles) {
		for (int i = 0; i < COUNT_OF_VEHICLES; i++) {
			String randModel = MODELS[new Random().nextInt(MODELS.length)];
			EnumVehicle randType = EnumVehicle.values()[new Random().nextInt(EnumVehicle.values().length)];
			int randYear = new Random().nextInt(YEAR_TO-YEAR_FROM) + YEAR_FROM;
			vehicles.add(new Vehicle(randModel,randType,randYear));
		}
	}
	
	private static void setDriversVehicles(List<IDriver> drivers, List<Vehicle> vehicles) {
		for (IDriver d : drivers) {
			for (int i = 0; i < COUNT_OF_VEHICLES_PER_DRIVER;) {
				int randVehicle = new Random().nextInt(vehicles.size());
				if (!vehicles.get(randVehicle).getOwner()) {
					((Driver) d).getVehicles().add(vehicles.get(randVehicle));
					vehicles.get(randVehicle).setOwner();
					i++;
				}
			}
		}
	}
	
	private static void buyVignettes(List<IDriver> drivers) {
		Collections.shuffle(drivers);
		for (int i = 0; i < drivers.size(); i++) {
			if (i%3 == 2) {
				int j = 0;
				do {
					int randVehicle = new Random().nextInt(((Driver) drivers.get(i)).getVehicles().size());
					int randDuration = new Random().nextInt(Date.values().length);
					if (drivers.get(i).buyVignetteSticker(randVehicle, Date.values()[randDuration])) {
						j++;
					}
				} while (j < 5);
			} else {
				drivers.get(i).buyVignetteStickerForAllVehicles();
			}
		}
	}
	
	private static void printDriversInfo(List<IDriver> drivers,LocalDate date) {
		for (IDriver d : drivers) {
			((Driver) d).PrintDriverInfo(date);
		}
	}
}
