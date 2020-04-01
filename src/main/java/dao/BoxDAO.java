package dao;

import java.util.List;

import models.*;

public interface BoxDAO {
	public Box getBoxByID(long id);
	public boolean operatonWBox(Box box, Instrument instrument,float amount,boolean type);
	public List<Box> getBoxByNumber(int number,int idLocation);
	public boolean createBox(Box box);
	public boolean deleteBox(int id);
}
