package dao;

import java.util.List;

import models.*;

public interface BoxDAO {
	public Box getBoxByID(long id);
	public boolean operatonWBox(Box box, Instrument instrument,float amount,boolean type);
	public Box getBoxByNumber(int number,Location loacation);
	public boolean createBox(int number, Location location, List<Instrument> instruments, List<Integer> instrumentsNumbers);
	public boolean createBox(int number, Location location);
	public boolean deleteBox(int id);
}
