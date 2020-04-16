package dao;

import java.util.List;

import models.Storage;

public interface StorageDAO {
	public Storage getStorageByID(long id);
	public List<Storage> getStorageByinstrument( long instrumentId);
	public List<Storage> getStorageByBox(long boxId);
	public boolean createStorage(Storage storage);
	public boolean deleteStorage(long id);
	public boolean hasError();
	public boolean updateStorage(long id,Storage storage);
	public void closeConection();
}
