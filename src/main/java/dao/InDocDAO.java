package dao;

import java.util.List;

import models.Box;
import models.InDoc;
import models.Instrument;
import models.Location;

public interface InDocDAO {
	public boolean createInDoc(Location inLocation, Box inBox, String date, Instrument instrument, float amount);
	public InDoc getInDocById(int id);
	public List<InDoc> getInDocByDate(String date);
	public List<InDoc> getInDocByInstrum(long id);
	public List<InDoc> getInDocByBox(long id);
	public boolean deleteInDoc(int id);

}
