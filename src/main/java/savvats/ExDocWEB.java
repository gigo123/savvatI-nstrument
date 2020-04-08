package savvats;

import java.time.LocalDate;



public class ExDocWEB {
	private long id;
	private String outLocation;
	private String inLocation;
	private String outBox;
	private String inBox;
	private LocalDate date;
	private String instrument;
	private float amount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOutLocation() {
		return outLocation;
	}
	public void setOutLocation(String outLocation) {
		this.outLocation = outLocation;
	}
	public String getInLocation() {
		return inLocation;
	}
	public void setInLocation(String inLocation) {
		this.inLocation = inLocation;
	}
	public String getOutBox() {
		return outBox;
	}
	public void setOutBox(String outBox) {
		this.outBox = outBox;
	}
	public String getInBox() {
		return inBox;
	}
	public void setInBox(String inBox) {
		this.inBox = inBox;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public ExDocWEB(String outLocation, String inLocation, String outBox, String inBox, LocalDate date,
			String instrument, float amount) {
		super();
		this.outLocation = outLocation;
		this.inLocation = inLocation;
		this.outBox = outBox;
		this.inBox = inBox;
		this.date = date;
		this.instrument = instrument;
		this.amount = amount;
	}
	public ExDocWEB() {
		
	}
	@Override
	public String toString() {
		return "ExDocWEB [id=" + id + ", outLocation=" + outLocation + ", inLocation=" + inLocation + ", outBox="
				+ outBox + ", inBox=" + inBox + ", date=" + date + ", instrument=" + instrument + ", amount=" + amount
				+ "]";
	}
	
	
}
