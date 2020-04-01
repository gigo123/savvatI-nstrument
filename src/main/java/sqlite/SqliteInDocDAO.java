package sqlite;

import java.util.List;

import dao.InDocDAO;
import models.Box;
import models.InDoc;
import models.Instrument;
import models.Location;

public class SqliteInDocDAO implements InDocDAO{

	@Override
	public boolean createInDoc(Location inLocation, Box inBox, String date, Instrument instrument, float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InDoc getInDocById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InDoc> getInDocByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InDoc> getInDocByInstrum(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InDoc> getInDocByBox(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteInDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
