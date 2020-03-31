package models;

public class Location {
	private int id;
	private String name;
	private boolean boxes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	

}
