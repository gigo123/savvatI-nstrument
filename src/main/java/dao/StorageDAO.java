package dao;

import models.Storage;

public interface StorageDAO {
	public Storage getStorageByID(long id);
	public Storage getStorageByinstrument();
	public Storage getStorageByBox();
	public boolean createStorage(Storage storage);
	public boolean deleteStorage(long id);
	public boolean hasError();
}
