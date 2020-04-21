package models;

import javax.validation.constraints.PositiveOrZero;


public class Box {
	private long id;
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int number;
	private Location location;
	private boolean notEmpty;
	
	
	public boolean isNotEmpty() {
		return notEmpty;
	}
	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Box(int number, Location location) {
		super();
		this.number = number;
		this.location = location;
	}
	public Box() {
	}
	@Override
	public String toString() {
		return "Box [id=" + id + ", number=" + number + ", location=" + location + "]";
	}
	
}
