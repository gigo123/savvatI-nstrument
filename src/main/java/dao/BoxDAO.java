package dao;

import models.*;

public interface BoxDAO {
	public Box getBoxByID(long id);
	public Box getBoxByNumber(int number,int idLocation);
	public boolean createBox(Box box);
	public boolean deleteBox(long id);
	public boolean hasError();
}
