package models;

import java.sql.Date;

public class ExDoc {
	private int id;
	private Location outLocation;
	private Location inLocation;
	private Box outBox;
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
	public Location getOutLocation() {
		return outLocation;
	}
	public void setOutLocation(Location outLocation) {
		this.outLocation = outLocation;
	}
	public Location getInLocation() {
		return inLocation;
	}
	public void setInLocation(Location inLocation) {
		this.inLocation = inLocation;
	}
	public Box getOutBox() {
		return outBox;
	}
	public void setOutBox(Box outBox) {
		this.outBox = outBox;
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
	public void setInstrument(Instrument istrument) {
		this.instrument = istrument;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public ExDoc(Location outLocation, Location inLocation, Box outBox, Box inBox, Date date, Instrument instrument,
			float amount) {
		super();
		this.outLocation = outLocation;
		this.inLocation = inLocation;
		this.outBox = outBox;
		this.inBox = inBox;
		this.date = date;
		this.instrument = instrument;
		this.amount = amount;
	}
	public ExDoc() {};
	
	
}
