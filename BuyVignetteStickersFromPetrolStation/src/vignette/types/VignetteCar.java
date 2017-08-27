package vignette.types;

import enums.Color;
import enums.Date;
import vignette.Vignette;

public class VignetteCar extends Vignette {

	public VignetteCar(Date duration) {
		super(duration,Color.RED);
	}

	@Override
	public int lepni() {
		return 5;
	}
	
}
