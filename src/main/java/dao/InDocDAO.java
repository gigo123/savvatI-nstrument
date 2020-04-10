package dao;

import java.time.LocalDate;
import java.util.List;
import models.InDoc;


public interface InDocDAO {
	public boolean createInDoc(InDoc inDoc);
	public InDoc getInDocById(long id);
	public List<InDoc> getInDocByDate(LocalDate date);
	public List<InDoc> getInDocByInstrum(long id);
	public List<InDoc> getInDocByBox(long idB,long idL);
	public boolean deleteInDoc(long id);
	public boolean hasError();
	public void closeConection();

}
