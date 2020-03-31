package sqlite;

import java.util.List;

import dao.InstrumentDAO;
import models.Instrument;
import models.Location;

public class SqliteInDocDAO implements InstrumentDAO{

	@Override
	public Instrument getInstrumentByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument getInstrumentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instrument> getInstrumentByNameL(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instrument> getInstrumentByBox(long idB, int idL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instrument> getInstrumentByLocation(Location loacation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instrument> getAllInstrument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createInstrument(String name, String measure, String comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createInstrument(String name, String measure) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteInstrument(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
