package dao;

import java.util.List;

import models.Box;
import models.OutDoc;
import models.Instrument;
import models.Location;

public interface OutDocDAO {
	public boolean OutDoc(Location outLocation, Box outBox, String date, Instrument instrument, float amount);
	public OutDoc getOutDocById(int id);
	public List<OutDoc> getOutDocByDate(String date);
	public List<OutDoc> getOutDocByInstrum(long id);
	public List<OutDoc> getOutDocByBox(long id);
	public boolean deleteInDoc(int id);
}
