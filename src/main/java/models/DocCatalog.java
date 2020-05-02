package models;

import java.time.LocalDate;

public class DocCatalog {
	private long id;
	private int year;
	private int number;
	private String numberString;
	private LocalDate date;
	private int totalInstrum;
	private float totalAmount;
	
	public int getTotalInstrum() {
		return totalInstrum;
	}
	public void setTotalInstrum(int totalInstrum) {
		this.totalInstrum = totalInstrum;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNumberString() {
		return numberString;
	}
	public void setNumberString(String numberString) {
		this.numberString = numberString;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	
	
	public DocCatalog(int year, int number, String numberString, LocalDate date) {
		super();
		this.year = year;
		this.number = number;
		this.numberString = numberString;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "DocCatalog [id=" + id + ", year=" + year + ", number=" + number + ", numberString=" + numberString
				+ ", date=" + date + "]";
	}
	public DocCatalog() {
		
	}
	
}
