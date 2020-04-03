package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteExDocDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteLocationDAO;

class SQLExDoc {

	SqliteBoxDAO boxDAO;
	SqliteLocationDAO locationDAO;
	SqliteInstrumentDAO instrumentDAO;
	SqliteExDocDAO exDocDAO;
	
	void initConnection() {
		SQLConectionHolder conectionHolder = new SQLConectionHolder();
		conectionHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		boxDAO = new SqliteBoxDAO();
		boxDAO.setConectionHolder(conectionHolder);
		locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectionHolder);
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectionHolder);
		exDocDAO = new SqliteExDocDAO();
		exDocDAO.setConectionHolder(conectionHolder);
	}

	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = exDocDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createExDoc() {
		initConnection();
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		Instrument inst = instrumentDAO.getInstrumentByID(1);
		Location location =  locationDAO.getLocById(1);
		Box box = boxDAO.getBoxByID(1);
		LocalDate today = LocalDate.now();
		ExDoc exDoc = new ExDoc(location, location, box, box, today, inst, 1);
		exDocDAOc.createExDoc(exDoc);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		exDocDAO.createExDoc(exDoc);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getExDocById() {
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		exDocDAOc.getExDocById(1);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		ExDoc exdoc =exDocDAO.getExDocById(1);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getExDocByBox() {
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		exDocDAOc.getExDocByBox(1, 1);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		List<ExDoc> exDocList  = exDocDAO.getExDocByBox(1, 1);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getExDocByInstrument() {
		initConnection();
		List<ExDoc> exDocList  = exDocDAO.getExDocByInstrum(instrumentDAO.getInstrumentByID(1).getId());
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getExDocByDate() {
		initConnection();
		List<ExDoc> exDocList  = exDocDAO.getExDocByDate(LocalDate.now());
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
}
