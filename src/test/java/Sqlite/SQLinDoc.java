package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Box;
import models.InDoc;
import models.Instrument;
import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteInDocDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteLocationDAO;

class SQLinDoc {
	SqliteBoxDAO boxDAO;
	SqliteLocationDAO locationDAO;
	SqliteInstrumentDAO instrumentDAO;
	SqliteInDocDAO inDocDAO;
	
	
	void initConnection() {
		SQLConectionHolder conectionHolder = new SQLConectionHolder();
		conectionHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		boxDAO = new SqliteBoxDAO();
		boxDAO.setConectionHolder(conectionHolder);
		locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectionHolder);
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectionHolder);
		inDocDAO = new SqliteInDocDAO();
		inDocDAO.setConectionHolder(conectionHolder);
	}
	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = inDocDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createInDoc() {
		initConnection();
		SqliteInDocDAO  inDocDAOc = new SqliteInDocDAO();
		Instrument inst = instrumentDAO.getInstrumentByID(1);
		Location location =  locationDAO.getLocById(1);
		Box box = boxDAO.getBoxByID(1);
		LocalDate today = LocalDate.now();
		InDoc exDoc = new InDoc(location,  box, today, inst, 1);
		inDocDAOc.createInDoc(exDoc);
		boolean error = inDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		inDocDAO.createInDoc(exDoc);
		error = inDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getInDocById() {
		SqliteInDocDAO  inDocDAOc = new SqliteInDocDAO();
		inDocDAOc.getInDocById(1);
		boolean error = inDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		InDoc exdoc =inDocDAO.getInDocById(1);
		error = inDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getInDocByBox() {
		SqliteInDocDAO  inDocDAOc = new SqliteInDocDAO();
		inDocDAOc.getInDocByBox(1, 1);
		boolean error = inDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		List<InDoc> exDocList  = inDocDAO.getInDocByBox(1, 1);
		error = inDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getInDocByInstrument() {
		initConnection();
		List<InDoc> exDocList  = inDocDAO.getInDocByInstrum(instrumentDAO.getInstrumentByID(1).getId());
		boolean error = inDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getInDocByDate() {
		initConnection();
		List<InDoc> exDocList  = inDocDAO.getInDocByDate(LocalDate.now());
		boolean error = inDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}

}
