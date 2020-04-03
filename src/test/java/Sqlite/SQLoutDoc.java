package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Box;
import models.OutDoc;
import models.Instrument;
import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteOutDocDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteLocationDAO;

class SQLoutDoc {

	SqliteBoxDAO boxDAO;
	SqliteLocationDAO locationDAO;
	SqliteInstrumentDAO instrumentDAO;
	SqliteOutDocDAO outDocDAO;
	
	
	void initConnection() {
		SQLConectionHolder conectionHolder = new SQLConectionHolder();
		conectionHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		boxDAO = new SqliteBoxDAO();
		boxDAO.setConectionHolder(conectionHolder);
		locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectionHolder);
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectionHolder);
		outDocDAO = new SqliteOutDocDAO();
		outDocDAO.setConectionHolder(conectionHolder);
	}
	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = outDocDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createOutDoc() {
		initConnection();
		SqliteOutDocDAO  outDocDAOc = new SqliteOutDocDAO();
		Instrument inst = instrumentDAO.getInstrumentByID(1);
		Location location =  locationDAO.getLocById(1);
		Box box = boxDAO.getBoxByID(1);
		LocalDate today = LocalDate.now();
		OutDoc outDoc = new OutDoc(location,  box, today, inst, 1);
		outDocDAOc.createOutDoc(outDoc);
		boolean error = outDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		outDocDAO.createOutDoc(outDoc);
		error = outDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getOutDocById() {
		SqliteOutDocDAO  outDocDAOc = new SqliteOutDocDAO();
		outDocDAOc.getOutDocById(1);
		boolean error = outDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		OutDoc exdoc =outDocDAO.getOutDocById(1);
		error = outDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getOutDocByBox() {
		SqliteOutDocDAO  outDocDAOc = new SqliteOutDocDAO();
		outDocDAOc.getOutDocByBox(1, 1);
		boolean error = outDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		List<OutDoc> exDocList  = outDocDAO.getOutDocByBox(1, 1);
		error = outDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getOutDocByInstrument() {
		initConnection();
		List<OutDoc> exDocList  = outDocDAO.getOutDocByInstrum(instrumentDAO.getInstrumentByID(1).getId());
		boolean error = outDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getOutDocByDate() {
		initConnection();
		List<OutDoc> exDocList  = outDocDAO.getOutDocByDate(LocalDate.now());
		boolean error = outDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}

}
