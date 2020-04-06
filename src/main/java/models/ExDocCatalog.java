package models;

import java.time.LocalDate;

public class ExDocCatalog {
	private long id;
	private String numberString;
	private int number;
	private LocalDate date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumberString() {
		return numberString;
	}
	public void setNumberString(String numberString) {
		this.numberString = numberString;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public ExDocCatalog(String numberString, int number, LocalDate date) {
		super();
		this.numberString = numberString;
		this.number = number;
		this.date = date;
	}
	public ExDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "ExDocCatalog [id=" + id + ", numberString=" + numberString + ", number=" + number + ", date=" + date
				+ "]";
	}
	
	
}
