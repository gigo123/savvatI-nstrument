package dao;

import java.util.List;

import models.Box;
import models.OutDoc;
import models.Instrument;
import models.Location;

public interface OutDocDAO {
	public boolean OutDoc(OutDoc outDoc);
	public OutDoc getOutDocById(int id);
	public List<OutDoc> getOutDocByDate(String date);
	public List<OutDoc> getOutDocByInstrum(long id);
	public List<OutDoc> getOutDocByBox(long idB,long idL);
	public boolean deleteInDoc(int id);
	public boolean hasError();
}
