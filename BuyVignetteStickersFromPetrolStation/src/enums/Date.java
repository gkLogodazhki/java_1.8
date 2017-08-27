package enums;

public enum Date {

	DAY(1,1), MONTH(31,10), YEAR(365,60);

	private final int code;
	private final int days;

	private Date(int days,int code) {
		this.days = days;
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
	public int getDays() {
		return this.days;
	}

	public Date getDate(int code) {
		return Date.values()[code];
	}
}
