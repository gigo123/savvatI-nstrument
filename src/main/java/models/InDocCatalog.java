package models;

import java.time.LocalDate;

public class InDocCatalog extends DocCatalog {

	public InDocCatalog(int year, int number,String numberString, LocalDate date) {
		super(year,number, numberString,date);
		
	}
	public InDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "InDocCatalog [getNumberString()=" + getNumberString() + ", getId()=" + getId() + ", getYear()="
				+ getYear() + ", getNumber()=" + getNumber() + ", getDate()=" + getDate() + "]";
	}
	
	

}
