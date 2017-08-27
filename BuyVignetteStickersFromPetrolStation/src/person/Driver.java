package person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import enums.Date;
import interfaces.IDriver;
import interfaces.IPetrol;
import vehicles.Vehicle;

public class Driver implements IDriver {

	private String name;
	private ArrayList<Vehicle> vehicles;
	private int money;
	private IPetrol petrol;

	public Driver(String name, int money, IPetrol petrol) {
		this.name = (!"".equals(name)) ? name : "No name";
		this.vehicles = new ArrayList<>();
		this.money = (money > 0) ? money : 0;
		this.petrol = petrol;
	}

	@Override
	public boolean buyVignetteSticker(int index, Date duration) {
		if (this.vehicles.get(index).getVignette() == null) {
			if (this.petrol.sellVignette(this, vehicles.get(index), duration)) {
				this.vehicles.get(index).setVignette(duration);
				this.money -= this.vehicles.get(index).getVignette().getPrice();
				return true;
			}
		}
		return false;
	}

	@Override
	public void buyVignetteStickerForAllVehicles() {
		for (Vehicle v : vehicles) {
			int randDuration = new Random().nextInt(Date.values().length);
			if (this.petrol.sellVignette(this, v, Date.values()[randDuration])) {
				v.setVignette(Date.values()[randDuration]);
				this.money -= v.getVignette().getPrice();
			}
		}
	}

	@Override
	public int checkVignette(LocalDate date) {
		int count = 0;
		for (Vehicle v : this.vehicles) {
			if (v.getVignette() == null || v.getVignette().getSoldDate().toEpochDay()
					+ v.getVignette().getDuration().getDays() < date.toEpochDay()) {
				System.out.println(count + 1 + ") " + v.toString());
				count++;
			}
		}
		return count;
	}

	@Override
	public String toString() {
		return "\nDriver [name=" + name + ", money=" + money + ", vehiclesCount=" + this.vehicles.size() + "]\n";
	}

	private StringBuilder printVehiclesInfo() {
		StringBuilder sb = new StringBuilder("");
		for (Vehicle v : this.vehicles) {
			sb.append("\n").append(v.toString());
		}
		return sb;
	}

	public ArrayList<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public int getMoney() {
		return this.money;
	}

	public void PrintDriverInfo(LocalDate date) {
		System.out.print(this.toString());
		System.out.printf("Vehicles with expire vignettes on %s :\n", date.toString());
		System.out.printf("Total : %d/%d\n",checkVignette(date), this.vehicles.size());
		// System.out.println(this.printVehiclesInfo());

	}

}
