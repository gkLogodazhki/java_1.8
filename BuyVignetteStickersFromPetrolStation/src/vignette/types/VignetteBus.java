package vignette.types;

import enums.Color;
import enums.Date;
import vignette.Vignette;

public class VignetteBus extends Vignette {

	public VignetteBus(Date duration) {
		super(duration,Color.BLUE);
	}

	@Override
	public int lepni() {
		return 20;
	}
	
}
