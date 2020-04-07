package savvats;

import java.time.LocalDate;



public class ExDocWEB {
	private long id;
	private String outLocationString;
	
	public String getOutLocationString() {
		return outLocationString;
	}
	public void setOutLocationString(String outLocationString) {
		this.outLocationString = outLocationString;
	}
	private String inBoxString;
	
	public String getInBoxString() {
		return inBoxString;
	}
	public void setInBoxString(String inBoxString) {
		this.inBoxString = inBoxString;
	}
	private long outLocation;
	private long inLocation;
	private long outBox;
	private long inBox;
	private LocalDate date;
	private long instrument;
	private float amount;
	
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
	@Override
	public String toString() {
		return "ExDocWEB [id=" + id + ", outLocationString=" + outLocationString + ", outLocation=" + outLocation
				+ ", inLocation=" + inLocation + ", outBox=" + outBox + ", inBox=" + inBox + ", date=" + date
				+ ", instrument=" + instrument + ", amount=" + amount + "]";
	}
	
	
}
