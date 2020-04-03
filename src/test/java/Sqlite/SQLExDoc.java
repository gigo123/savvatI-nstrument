package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

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
		
		System.out.println(java.sql.Date.valueOf(LocalDate.now().toString()));
	}
	@Test
	void createExDoc() {
		initConnection();
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		Instrument inst = instrumentDAO.getInstrumentByID(1);
		Location location =  locationDAO.getLocById(1);
		Box box = boxDAO.getBoxByID(1);
		//Date date =DATE 
		LocalDate today = LocalDate.now();
		ExDoc exDoc = new ExDoc(location, location, box, box, today, inst, 1);
		exDocDAOc.createExDoc(exDoc);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		//exDocDAO.createBox(box);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getBoxById() {
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		//exDocDAOc.getBoxByID(1);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		exDocDAO.getExDocById(1);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getBoxByNumber() {
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
	//	exDocDAOc.getBoxByNumber(1, 1);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
	//	Box box  = exDocDAO.getBoxByNumber(1, 1);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
	//	assertTrue(box.getNumber()==1,"must be test1");
	}

}
