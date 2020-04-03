package dao;

import java.time.LocalDate;
import java.util.List;

import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public interface ExDocDAO {
	public boolean createExDoc(ExDoc exDoc);
	public ExDoc getExDocById(long id);
	public List<ExDoc> getExDocByDate(LocalDate date);
	public List<ExDoc> getExDocByInstrum(long id);
	public List<ExDoc> getExDocByBox(long idB, long idL);
	public boolean deleteExDoc(long id);
	public boolean hasError();

}
