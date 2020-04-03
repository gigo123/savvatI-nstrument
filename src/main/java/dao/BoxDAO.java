package dao;

import models.*;

public interface BoxDAO {
	public Box getBoxByID(long id);
	public boolean operatonWBox(Box box, Instrument instrument,float amount,boolean type);
	public Box getBoxByNumber(int number,int idLocation);
	public boolean createBox(Box box);
	public boolean deleteBox(long id);
	public boolean hasError();
}
