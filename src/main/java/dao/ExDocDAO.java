package dao;

import java.util.List;

import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public interface ExDocDAO {
	public boolean createExDoc(ExDoc exDoc);
	public ExDoc getExDocById(int id);
	public List<ExDoc> getExDocByDate(String date);
	public List<ExDoc> getExDocByInstrum(long id);
	public List<ExDoc> getExDocByBox(long idB, long idL);
	public boolean deleteExDoc(int id);
	public boolean hasError();

}
