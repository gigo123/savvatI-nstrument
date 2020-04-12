package models;

public class DocModel {
	private long id;
	private Location outLocation;
	private Box outBox;
	private DocCatalog catalogId;
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
	public Box getOutBox() {
		return outBox;
	}
	public void setOutBox(Box outBox) {
		this.outBox = outBox;
	}
	public DocCatalog getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(DocCatalog catalogId) {
		this.catalogId = catalogId;
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
	@Override
	public String toString() {
		return "DocModel [id=" + id + ", outLocation=" + outLocation + ", outBox=" + outBox + ", catalogId=" + catalogId
				+ ", instrument=" + instrument + ", amount=" + amount + "]";
	}
	public DocModel(Location outLocation, Box outBox, DocCatalog catalogId, Instrument instrument, float amount) {
		super();
		this.outLocation = outLocation;
		this.outBox = outBox;
		this.catalogId = catalogId;
		this.instrument = instrument;
		this.amount = amount;
	}
	
	public DocModel() {
	}

}
