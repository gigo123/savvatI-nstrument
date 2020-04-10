package dao;

import java.util.List;

import models.Box;
import models.Instrument;
import models.Storage;

public interface StorageDAO {
	public Storage getStorageByID(long id);
	public List<Storage> getStorageByinstrument( Instrument instrument);
	public List<Storage> getStorageByBox(Box box);
	public boolean createStorage(Storage storage);
	public boolean deleteStorage(long id);
	public boolean hasError();
}
