package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Instrument {
	private long id;
	 @Size(min = 4, max = 30, message = "имя должно бить от 4 до 20 символов")
	private String name;
	private String comment;
	@NotEmpty
	private String measure;
	private float totalNumber;
	
	
	public float getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(float totalNumber) {
		this.totalNumber = totalNumber;
	}
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
	public Instrument() {}
	@Override
	public String toString() {
		return "Instrument [id=" + id + ", name=" + name + ", comment=" + comment + ", measure=" + measure
				+ ", totalNumber=" + totalNumber + "]";
	}
	
	
}
