package savvats;

import java.time.LocalDate;



public class ExDocWEB {
	private long id;
	private long outLocation;
	private long inLocation;
	private long outBox;
	private long inBox;
	private LocalDate date;
	private long instrument;
	private float amount;
	private String locations;
	private String boxes;
	private String instruments;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOutLocation() {
		return outLocation;
	}
	public void setOutLocation(long outLocation) {
		this.outLocation = outLocation;
	}
	public long getInLocation() {
		return inLocation;
	}
	public void setInLocation(long inLocation) {
		this.inLocation = inLocation;
	}
	public long getOutBox() {
		return outBox;
	}
	public void setOutBox(long outBox) {
		this.outBox = outBox;
	}
	public long getInBox() {
		return inBox;
	}
	public void setInBox(long inBox) {
		this.inBox = inBox;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getInstrument() {
		return instrument;
	}
	public void setInstrument(long instrument) {
		this.instrument = instrument;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public ExDocWEB(long outLocation, long inLocation, long outBox, long inBox, LocalDate date, long instrument,
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
	
	public ExDocWEB(long outLocation, long inLocation, long outBox, long inBox, long instrument,
			float amount) {
		super();
		this.outLocation = outLocation;
		this.inLocation = inLocation;
		this.outBox = outBox;
		this.inBox = inBox;
		this.date =  LocalDate.now();
		this.instrument = instrument;
		this.amount = amount;
	}
	public ExDocWEB() {
		
	}
}
