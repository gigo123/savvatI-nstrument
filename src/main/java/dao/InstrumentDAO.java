package dao;

import java.util.List;

import models.Box;
import models.Instrument;
import models.Location;

public interface InstrumentDAO {
	public Instrument getInstrumentByID(long id);
	public Instrument getInstrumentByName(String name);
	public List<Box> getInstrumentByNameL(String name);
	public List<Box> getInstrumentByBox(long idB,int idL);
	public List<Box> getInstrumentByLocation(Location loacation);
	public List<Box> getAllInstrument();
	public boolean createInstrument(Instrument instrument);
	public boolean deleteInstrument(long id);
	public boolean hasError();
}
