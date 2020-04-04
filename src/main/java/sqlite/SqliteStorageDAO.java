package sqlite;

import dao.StorageDAO;
import models.Storage;

public class SqliteStorageDAO  implements StorageDAO{

	@Override
	public Storage getStorageByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Storage getStorageByinstrument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Storage getStorageByBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createStorage(Storage storage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStorage(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

}
