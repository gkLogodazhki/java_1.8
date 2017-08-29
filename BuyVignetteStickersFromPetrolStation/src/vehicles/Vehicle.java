package vehicles;

import enums.Date;
import enums.EnumVehicle;
import vignette.Vignette;
import vignette.types.IVignette;
import vignette.types.VignetteBus;
import vignette.types.VignetteCar;
import vignette.types.VignetteTruck;

public class Vehicle implements IVehicle {

	private String model;
	private Vignette vignette;
	private int year;
	private EnumVehicle vehicleType;
	private boolean hasOwner;

	public Vehicle(String model, EnumVehicle vehicleType, int year) {
		this.model = (!"".equals(model)) ? model : "Golf";
		this.vehicleType = vehicleType;
		this.year = (year > 1960 && year <= 2017) ? year : 1960;
		this.hasOwner = false;
	}
	
	public void setOwner() {
		this.hasOwner = true;
	}
	public boolean getOwner() {
		return this.hasOwner;
	}

	public void setVignette(Date duration) {
		final IVignette vignette;
		if (this.vehicleType.equals(EnumVehicle.CAR)) {
			vignette = new VignetteCar(duration);
		} else if (this.vehicleType.equals(EnumVehicle.TRUCK)) {
			vignette = new VignetteTruck(duration);
		} else if (this.vehicleType.equals(EnumVehicle.BUS)) {
			vignette = new VignetteBus(duration);
		} else {
			vignette = null;
		}
		this.vignette = (Vignette) vignette;
		this.vignette.setSoldDate();
	}

	@Override
	public String toString() {
		return "Vehicle [model=" + model + ", Vignette [vignette=" + vignette + ", year=" + year + ", vehicleType=" + vehicleType + "]";
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;

		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;

		if (vehicleType != other.vehicleType)
			return false;

		if (vignette == null) {
			if (other.vignette != null)
				return false;
		} else if (!vignette.equals(other.vignette))
			return false;

		return (year != other.year) ? false : true;
	}

	public Vignette getVignette() {
		return this.vignette;
	}
	
	public EnumVehicle getVehicleType() {
		return this.vehicleType;
	}

}
