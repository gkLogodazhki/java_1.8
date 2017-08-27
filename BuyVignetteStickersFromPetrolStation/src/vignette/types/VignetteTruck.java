package vignette.types;

import enums.Color;
import enums.Date;
import vignette.Vignette;

public class VignetteTruck extends Vignette {

	public VignetteTruck(Date duration) {
		super(duration,Color.GREEN);
	}

	@Override
	public int lepni() {
		return 10;
	}

}
