package models;


public class ExDoc  extends DocModel{

	private Location inLocation;

	private Box inBox;
	
	
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
	
	public ExDoc(Location outLocation, Box outBox, Location inLocation, Box inBox,DocCatalog catalogId, Instrument instrument, float amount) {
		super(outLocation, outBox, catalogId, instrument, amount);
		this.inBox=inBox;
		this.inLocation= inLocation;	
	}
	
	@Override
	public String toString() {
		return "ExDoc [id=" + getId()+ ", outLocation=" + getOutLocation() + ", outBox=" +getOutBox() + ", catalogId=" + getCatalogId() + 
				", instrument=" + getInstrument() + ", amount=" + getAmount() +"inLocation=" + inLocation + ", inBox=" + inBox + "]";
	}
	public ExDoc() {};
	
	
}
