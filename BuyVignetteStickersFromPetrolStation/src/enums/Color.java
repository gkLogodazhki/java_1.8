package enums;

public enum Color {

	RED(5), GREEN(7), BLUE(9);

	private final int code;

	private Color(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

}
