package dao;

import java.util.List;

import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public interface ExDocDAO {
	public boolean createExDoc(Location outLocation, Location inLocation, Box outBox, Box inBox, String date, Instrument instrument,
			float amount);
	public ExDoc getExDocById(int id);
	public List<ExDoc> getExDocByDate(String date);
	public List<ExDoc> getExDocByInstrum(long id);
	public List<ExDoc> getExDocByBox(long id);
	public boolean deleteExDoc(int id);

}
