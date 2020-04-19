package dao;

import java.util.List;


import models.Instrument;
import models.Storage;

public interface InstrumentDAO {
	public Instrument getInstrumentByID(long id);
	public Instrument getInstrumentByName(String name);
	public List<Instrument> getInstrumentByNameL(String name);
	public boolean updateInstrument(Instrument instrument);
	public List<Instrument> getAllInstrument();
	public boolean createInstrument(Instrument instrument);
	public boolean deleteInstrument(long id);
	public boolean hasError();
	public void closeConection();
}
