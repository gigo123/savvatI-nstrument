package models;

import java.sql.Date;

public class InDoc {
	private int id;
	private Location inLocation;
	private Box inBox;
	private Date date;
	private Instrument instrument;
	private float amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Location getInLocation() {
		return inLocation;
	}
	public void setInLocation(Location inLocation) {
		this.inLocation = inLocation;
	}
	public Box getInBox() {
		return inBox;
	}
	public void setInBox(Box inBox) {
		this.inBox = inBox;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public InDoc(Location inLocation, Box inBox, Date date, Instrument instrument, float amount) {
		super();
		this.inLocation = inLocation;
		this.inBox = inBox;
		this.date = date;
		this.instrument = instrument;
		this.amount = amount;
	}
	public InDoc() {};
	
}
