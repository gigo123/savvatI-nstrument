package models;

import java.time.LocalDate;

public class OutDocCatalog extends DocCatalog {

	public OutDocCatalog(String numberString, int number, LocalDate date) {
		super(numberString,number, date);
		
	}
	public OutDocCatalog() {
		
	}
	@Override
	public String toString() {
		return "OutDocCatalog [id=" + getId() + ", numberString=" + getNumberString() 
				+ ", number=" + getNumber() + ", date=" + getDate() +"]";
	}
	

}
