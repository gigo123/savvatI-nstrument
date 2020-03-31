package dao;

import java.util.List;

import models.Box;
import models.Instrument;
import models.Location;

public interface InstrumentDAO {
	public Instrument getInstrumentByID(long id);
	public Instrument getInstrumentByName(String name);
	public List<Instrument> getInstrumentByNameL(String name);
	public List<Instrument> getInstrumentByBox(long idB,int idL);
	public List<Instrument> getInstrumentByLocation(Location loacation);
	public List<Instrument> getAllInstrument();
	public boolean createInstrument(String name, String measure, String comment);
	public boolean createInstrument(String name, String measure);
	public boolean deleteInstrument(int id);
}
