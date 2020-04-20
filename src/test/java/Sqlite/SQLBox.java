package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;
import models.Box;
import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteLocationDAO;

class SQLBox {
	SqliteBoxDAO boxDAO;
	SqliteLocationDAO locationDAO;
	SqliteInstrumentDAO instrumentDAO;
	
	void initConnection() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		boxDAO = new SqliteBoxDAO();
		boxDAO.setConectionHolder(conectHolder);
		locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectHolder);
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectHolder);
	}

	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = boxDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createBox() {
		initConnection();
		SqliteBoxDAO  boxDAOc = new SqliteBoxDAO();
		Location location =  locationDAO.getLocById(1);
		Box box = new Box(1, location);
		boxDAOc.createBox(box);
		boolean error = boxDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		boxDAO.createBox(box);
		error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getBoxById() {
		SqliteBoxDAO  boxDAOc = new SqliteBoxDAO();
		boxDAOc.getBoxByID(1);
		boolean error = boxDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Box box = boxDAO.getBoxByID(9);
		error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(box!=null,"must not be null");
	}
	@Test
	void getBoxByNumber() {
		SqliteBoxDAO  boxDAOc = new SqliteBoxDAO();
		boxDAOc.getBoxByNumber(0, 1);
		boolean error = boxDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Box box  = boxDAO.getBoxByNumber(0, 1);
		error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(box.getNumber()==0,"must be test1");
	}
	@Test
	void getAllBox() {
		initConnection();
		List<Box> boxList  = boxDAO.getAllBox();
		boolean error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(boxList.size()!=0,"must be at lease one element");
	}
	@Test
	void getAllBoxBylocotion() {
		initConnection();
		List<Box> boxList  = boxDAO.getAllBoxByLocation(1);
		boolean error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(boxList.size()!=0,"must be at lease one element");
	}
	/*@Test
	@AfterClass
	void deleteBox() {
		initConnection();
		Box box  = boxDAO.getBoxByNumber(1, 1);
		boolean error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= box.getId();
		boxDAO.deleteBox(id);
		box  = boxDAO.getBoxByID(id);
		assertTrue(box==null,"box must be null(deletet)");
	}
*/
}
