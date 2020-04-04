package models;

public class Storage {
	private long id;
	private Box box ;
	private Instrument instrument;
	private float amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
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
	public Storage(Box box, Instrument instrument, float amount) {
		super();
		this.box = box;
		this.instrument = instrument;
		this.amount = amount;
	}
	public Storage() {
		
	}
	@Override
	public String toString() {
		return "Storage [id=" + id + ", box=" + box + ", instrument=" + instrument + ", amount=" + amount + "]";
	}
	
}
