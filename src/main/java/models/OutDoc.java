package models;

import java.time.LocalDate;

public class OutDoc {
	private int id;
	private Location outLocation;
	private Box outBox;
	private LocalDate date;
	private Instrument instrument;
	private float amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Location getOutLocation() {
		return outLocation;
	}
	public void setOutLocation(Location outLocation) {
		this.outLocation = outLocation;
	}
	public Box getOutBox() {
		return outBox;
	}
	public void setOutBox(Box outBox) {
		this.outBox = outBox;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public OutDoc(Location outLocation, Box outBox, LocalDate date, Instrument instrument, float amount) {
		super();
		this.outLocation = outLocation;
		this.outBox = outBox;
		this.date = date;
		this.instrument = instrument;
		this.amount = amount;
	}
	public OutDoc() {
	}
}
