package models;


public class InDoc  extends DocModel{
	
	public InDoc(Location outLocation, Box outBox, DocCatalog catalogId, Instrument instrument, float amount) {
		super(outLocation, outBox, catalogId, instrument, amount);
	}
	public InDoc() {};
	
}
