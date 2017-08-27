package vignette;

import java.time.LocalDate;

import enums.Color;
import enums.Date;
import interfaces.IVignette;

public abstract class Vignette implements IVignette {

	private LocalDate soldDate;
	private Color color;
	private Date duration;
	
	public Vignette(Date duration,Color color){
		this.color = color;
		this.duration = duration;
	}
	
	@Override
	public int getPrice() {
		return this.color.getCode()*this.duration.getCode();
	}
	
	@Override
	public String toString() {
		return "Vignette [color=" + color + ", duration=" + duration + "]";
	}
	
	public LocalDate getSoldDate() {
		return this.soldDate;
	}
	public void setSoldDate() {
		this.soldDate = LocalDate.now();
	}
	
	public Color getColor() {
		return this.color;
	}
	public Date getDuration() {
		return this.duration;
	}
	
	
}
