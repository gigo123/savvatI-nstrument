package models;

import java.util.List;

public class Box {
	private long id;
	private int number;
	private Location location;
	private Instrument instrument;
	private float amount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Instrument getInstruments() {
		return instrument;
	}
	public void setInstruments(Instrument instruments) {
		this.instrument = instruments;
	}
	public float getInstrumentsNumbers() {
		return amount;
	}
	public void setInstrumentsNumbers(float instrumentsNumbers) {
		amount = instrumentsNumbers;
	}
	public Box(int number, Location location, Instrument instruments, float instrumentsNumbers) {
		super();
		this.number = number;
		this.location = location;
		this.instrument = instruments;
		this.amount= instrumentsNumbers;
	}
	public Box(int number, Location location) {
		super();
		this.number = number;
		this.location = location;
	}
	public Box() {
	}
}
