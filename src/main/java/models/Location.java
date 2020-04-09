package models;

import javax.validation.constraints.Size;

public class Location {
	private long id;
	@Size(min = 4, max = 20, message = "имя должно бить от 4 до 20 символов")
	private String name;
	private boolean boxes;
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
	public boolean isBoxes() {
		return boxes;
	}
	public void setBoxes(boolean boxes) {
		this.boxes = boxes;
	}
	public Location() {
	}
	public Location(String name, boolean boxes) {
		super();
		this.name = name;
		this.boxes = boxes;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", boxes=" + boxes + "]";
	}
	

}
