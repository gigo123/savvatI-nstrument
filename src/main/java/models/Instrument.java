package models;

public class Instrument {
	private long id;
	private String name;
	private String comment;
	private String measure;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	public Instrument(String name, String measure) {
		super();
		this.name = name;
		this.measure = measure;
		this.comment="";
	}
	public Instrument(String name, String measure, String comment) {
		this(name, measure);
		this.comment = comment;
	}
	public Instrument() {};
}
