package dao;

import java.util.List;

import models.Box;
import models.InDoc;
import models.Instrument;
import models.Location;

public interface InDocDAO {
	public boolean createInDoc(InDoc inDoc);
	public InDoc getInDocById(int id);
	public List<InDoc> getInDocByDate(String date);
	public List<InDoc> getInDocByInstrum(long id);
	public List<InDoc> getInDocByBox(long idB,long idL);
	public boolean deleteInDoc(int id);

}
