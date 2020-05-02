package models;

import java.time.LocalDate;

public class OutDocCatalog extends DocCatalog {

	public OutDocCatalog(int year, int number,String numberString, LocalDate date) {
		super(year,number, numberString,date);
		
	}
	public OutDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "OutDocCatalog [getTotalInstrum()=" + getTotalInstrum() + ", getTotalAmount()=" + getTotalAmount()
				+ ", getNumberString()=" + getNumberString() + ", getId()=" + getId() + ", getYear()=" + getYear()
				+ ", getNumber()=" + getNumber() + ", getDate()=" + getDate() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	

}
