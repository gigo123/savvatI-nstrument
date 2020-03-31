package sqlite;

import java.util.List;

import dao.OutDocDAO;
import models.Box;
import models.Instrument;
import models.Location;

public class SqliteOutDocDAO implements OutDocDAO{

	@Override
	public boolean OutDoc(Location outLocation, Box outBox, String date, Instrument instrument, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public models.OutDoc getOutDocById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<models.OutDoc> getOutDocByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<models.OutDoc> getOutDocByInstrum(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<models.OutDoc> getOutDocByBox(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteInDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
