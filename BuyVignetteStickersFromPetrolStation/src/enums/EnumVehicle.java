package enums;

public enum EnumVehicle {

	CAR(Color.RED), TRUCK(Color.GREEN), BUS(Color.BLUE);

	private final Color color;
	private final int price;

	private EnumVehicle(Color color) {
		this.color = color;
		this.price = this.color.getCode();
	}

	public Color getColor() {
		return this.color;
	}
	
	public int getPrice() {
		return this.price;
	}
}
