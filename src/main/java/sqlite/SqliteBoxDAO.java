package sqlite;

import java.util.List;

import dao.BoxDAO;
import models.Box;
import models.Instrument;
import models.Location;

public class SqliteBoxDAO implements BoxDAO{

	@Override
	public Box getBoxByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean operatonWBox(Box box, Instrument instrument, float amount, boolean type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Box getBoxByNumber(int number, Location loacation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBox(int number, Location location, List<Instrument> instruments,
			List<Integer> instrumentsNumbers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createBox(int number, Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBox(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
