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
	public List<Instrument> getInstrumentByLocation(long locationId);
	public List<Instrument> getAllInstrument();
	public boolean createInstrument(Instrument instrument);
	public boolean deleteInstrument(long id);
	public boolean hasError();
	public void closeConection();
}
