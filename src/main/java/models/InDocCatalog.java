package models;

import java.time.LocalDate;

public class InDocCatalog extends DocCatalog {

	public InDocCatalog(String numberString, int number, LocalDate date) {
		super(numberString,number, date);
		
	}
	public InDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "InDocCatalog [id=" + getId() + ", numberString=" + getNumberString() 
				+ ", number=" + getNumber() + ", date=" + getDate() +"]";
	}
	

}
