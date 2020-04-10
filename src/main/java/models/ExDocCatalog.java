package models;

import java.time.LocalDate;

public class ExDocCatalog  extends DocCatalog{
	
	public ExDocCatalog(int year, int number,String numberString, LocalDate date) {
		super(year,number, numberString,date);
		
	}
	public ExDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "ExDocCatalog [getNumberString()=" + getNumberString() + ", getId()=" + getId() + ", getYear()="
				+ getYear() + ", getNumber()=" + getNumber() + ", getDate()=" + getDate() + "]";
	}
	
	
	
	
	
}
