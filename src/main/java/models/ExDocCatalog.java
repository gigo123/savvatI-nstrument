package models;

import java.time.LocalDate;

public class ExDocCatalog  extends DocCatalog{
	
	public ExDocCatalog(String numberString, int number, LocalDate date) {
		super(numberString,number, date);
		
	}
	public ExDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "ExDocCatalog [id=" + getId() + ", numberString=" + getNumberString() 
				+ ", number=" + getNumber() + ", date=" + getDate() +"]";
	}
	
	
}
