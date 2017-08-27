package petrolStations;

import java.util.ArrayList;
import java.util.List;

import enums.Date;
import interfaces.IPetrol;
import interfaces.IVignette;
import person.Driver;
import vehicles.Vehicle;
import vignette.Vignette;
import vignette.types.VignetteBus;
import vignette.types.VignetteCar;
import vignette.types.VignetteTruck;

public class Petrol implements IPetrol {

	private String name;
	private double money;
	private List<IVignette> vignettes;

	public Petrol(String name, double money) {
		this.name = (!"".equals(name)) ? name : "Bat Boiko";
		this.money = (money > 0)? money : 1000;
		this.vignettes = new ArrayList<IVignette>(10001);
		this.generateVignettes(10000);
	}

	@Override
	public void generateVignettes(int count) {
		for (int i = 0; i < count; i++) {
			int type = (int) (Math.random() * 3);
			int date = (int) (Math.random() * 3);
			IVignette vignette;
			switch (type) {
			case 0:
				vignette = new VignetteCar(Date.values()[date]);
				break;
			case 1:
				vignette = new VignetteTruck(Date.values()[date]);
				break;
			case 2:
				vignette = new VignetteBus(Date.values()[date]);
				break;
			default:
				vignette = null;
			}
			this.vignettes.add(this.findIndex(vignette), vignette);
		}
	}

	@Override
	public boolean sellVignette(Driver driver,Vehicle vehicle, Date duration) {
		for (IVignette v : this.vignettes) {
			if (vehicle.getVehicleType().getColor().equals(((Vignette) v).getColor())
					&& duration.equals(((Vignette) v).getDuration()) && driver.getMoney() > v.getPrice()) {
				this.money += v.getPrice();
				vehicle.setVignette(duration);
				this.vignettes.remove(v);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Petrol [name=" + name + ", money=" + money + "]";
	}
	
	public void printVignettes() {
		for (IVignette  v: this.vignettes) {
			System.out.println(v.toString());
		}
		System.out.println(this.vignettes.size());
	}

	public int findIndex(IVignette vignette) {
		int first = 0;
		int last = vignettes.size() - 1;
		int x = vignette.getPrice();

		int mid;
		while (first < last) {
			mid = (first + last) / 2;
			if (vignettes.get(mid).getPrice() == x)
				return mid;
			else if (vignettes.get(mid).getPrice() > x)
				last = mid - 1;
			else
				first = mid + 1;
		}
		if (first == vignettes.size())
			return vignettes.size();
		else if (vignettes.get(first).getPrice() > x)
			return first;
		else
			return first + 1;
	}
}
