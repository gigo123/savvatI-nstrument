package models;


public class ExDoc {
	private long id;
	private Location outLocation;
	private Location inLocation;
	private Box outBox;
	private Box inBox;
	private long catalogId;
	private Instrument instrument;
	private float amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	public long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(long catalogId) {
		this.catalogId = catalogId;
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
	public ExDoc(Location outLocation, Location inLocation, Box outBox, Box inBox, long catalogId, Instrument instrument,
			float amount) {
		super();
		this.outLocation = outLocation;
		this.inLocation = inLocation;
		this.outBox = outBox;
		this.inBox = inBox;
		this.catalogId = catalogId;
		this.instrument = instrument;
		this.amount = amount;
	}
	public ExDoc() {};
	
	
}
