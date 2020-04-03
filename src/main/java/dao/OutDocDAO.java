package dao;

import java.time.LocalDate;
import java.util.List;
import models.OutDoc;


public interface OutDocDAO {
	public boolean createOutDoc(OutDoc outDoc);
	public OutDoc getOutDocById(long id);
	public List<OutDoc> getOutDocByDate(LocalDate date);
	public List<OutDoc> getOutDocByInstrum(long id);
	public List<OutDoc> getOutDocByBox(long idB,long idL);
	public boolean deleteInDoc(long id);
	public boolean hasError();
}
