package sqlite;

import java.util.List;

import dao.ExDocDAO;
import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public class SqliteExDocDAO implements ExDocDAO {

	@Override
	public boolean createExDoc(Location outLocation, Location inLocation, Box outBox, Box inBox, String date,
			Instrument instrument, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ExDoc getExDocById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByInstrum(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByBox(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteExDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
