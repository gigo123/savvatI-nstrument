package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Box;
import models.Instrument;
import models.Location;
import models.OutDoc;
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
		Instrument inst = instrumentDAO.getInstrumentByID(1);
		Location location =  locationDAO.getLocById(1);
		Box box = new Box(1, location, inst, 1);
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
		 boxDAO.getBoxByID(1);
		error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getBoxByNumber() {
		SqliteBoxDAO  boxDAOc = new SqliteBoxDAO();
		boxDAOc.getBoxByNumber(1, 1);
		boolean error = boxDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Box box  = boxDAO.getBoxByNumber(1, 1);
		error = boxDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(box.getNumber()==1,"must be test1");
	}
	@Test
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

}
