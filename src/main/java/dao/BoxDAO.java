package dao;

import java.util.List;

import models.*;

public interface BoxDAO {
	public Box getBoxByID(long id);
	public Box getBoxByNumber(int number,long idLocation);
	public boolean createBox(Box box);
	public boolean deleteBox(long id);
	public boolean hasError();
	public boolean updateBox(long id,Box box);
	public List<Box> getAllBox();
	public List<Box> getNotEmptyBox();
	public List<Box> getNotEmptyBoxByLocation(long idLocation);
	public List<Box> getAllBoxByLocation(long idLocation);
	public void closeConection();
}
