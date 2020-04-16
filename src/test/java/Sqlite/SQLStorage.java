package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Box;
import models.Storage;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteStorageDAO;

class SQLStorage {
	SqliteBoxDAO boxDAO;
	SqliteInstrumentDAO instrumentDAO;
	SqliteStorageDAO storageDAO;
	
	void initConnection() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		boxDAO = new SqliteBoxDAO();
		boxDAO.setConectionHolder(conectHolder);
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectHolder);
		storageDAO = new SqliteStorageDAO();
		storageDAO.setConectionHolder(conectHolder);
	}

	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = storageDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createStorage() {
		initConnection();
		SqliteStorageDAO  boxDAOc = new SqliteStorageDAO();
		Box box = boxDAO.getBoxByID(7);
		Storage store = new Storage(box, instrumentDAO.getInstrumentByID(2), 1);
		boxDAOc.createStorage(store);
		boolean error = boxDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		storageDAO.createStorage(store);
		error = storageDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getStorageById() {
		initConnection();
		SqliteStorageDAO  storageDAOc = new SqliteStorageDAO();
		storageDAOc.getStorageByID(1);
		boolean error = storageDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		 storageDAO.getStorageByID(1);
		error = storageDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getStorageByBox() {
		
		initConnection();
		List<Storage> storeList  = storageDAO.getStorageByBox(9);
		boolean error = storageDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(storeList.size()>0,"must be test1");
		assertTrue((storeList.get(0).getBox().getId())!=0,"must be test1");
	}
	
	
/*	@Test
	void deleteBox() {
		initConnection();
		Box box  = storageDAO.getBoxByNumber(1, 1);
		boolean error = storageDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= box.getId();
		storageDAO.deleteBox(id);
		box  = storageDAO.getBoxByID(id);
		assertTrue(box==null,"box must be null(deletet)");
	}
*/
}
