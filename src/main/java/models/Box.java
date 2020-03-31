package models;

import java.util.List;

public class Box {
	private long id;
	private int number;
	private Location location;
	List<Instrument> instruments;
	List<Integer> instrumentsNumbers;
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
	public List<Instrument> getInstruments() {
		return instruments;
	}
	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
	public List<Integer> getInstrumentsNumbers() {
		return instrumentsNumbers;
	}
	public void setInstrumentsNumbers(List<Integer> instrumentsNumbers) {
		this.instrumentsNumbers = instrumentsNumbers;
	}
	public Box(int number, Location location, List<Instrument> instruments, List<Integer> instrumentsNumbers) {
		super();
		this.number = number;
		this.location = location;
		this.instruments = instruments;
		this.instrumentsNumbers = instrumentsNumbers;
	}
	public Box(int number, Location location) {
		super();
		this.number = number;
		this.location = location;
	}
	public Box() {
	}
}
