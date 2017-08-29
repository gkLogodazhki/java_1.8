package person;

import java.time.LocalDate;

import enums.Date;

public interface IDriver {

	int checkVignette(LocalDate date);

	boolean buyVignetteSticker(int index, Date duration);
	
	void buyVignetteStickerForAllVehicles();
	
}
