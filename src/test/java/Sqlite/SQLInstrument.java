package Sqlite;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.Box;
import models.Instrument;
import sqlite.SQLConectionHolder;
import sqlite.SqliteInstrumentDAO;

class SQLInstrument {

SqliteInstrumentDAO instrumentDAO;
	
	void initConnection() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		instrumentDAO = new SqliteInstrumentDAO();
		instrumentDAO.setConectionHolder(conectHolder);
	}

	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = instrumentDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createinstrument() {
		SqliteInstrumentDAO instrumentDAOc = new SqliteInstrumentDAO();
		Instrument inst = new Instrument("test1", "шт");
		instrumentDAOc.createInstrument(inst);
		boolean error = instrumentDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		instrumentDAO.createInstrument(inst);
		error = instrumentDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getInstById() {
		SqliteInstrumentDAO instrumentDAOc = new SqliteInstrumentDAO();
		instrumentDAOc.getInstrumentByID(1);
		boolean error = instrumentDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		 instrumentDAO.getInstrumentByID(1);
		error = instrumentDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getAllInst() {
		initConnection();
		List<Instrument> instrumList  = instrumentDAO.getAllInstrument();
		boolean error = instrumentDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(instrumList.size()!=0,"must not be 0");
	}
	@Test
	void getInstByName() {
		SqliteInstrumentDAO instrumentDAOc = new SqliteInstrumentDAO();
		instrumentDAOc.getInstrumentByName("test1");
		boolean error = instrumentDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Instrument instrum  = instrumentDAO.getInstrumentByName("test1");
		error = instrumentDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(instrum.getName().equals("test1"),"must be test1");
		assertTrue(instrum.getMeasure().equals("шт"),"must be false");
	}
	@Test
	void deleteInstrument() {
		initConnection();
		Instrument instrum = instrumentDAO.getInstrumentByName("test1");
		boolean error = instrumentDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= instrum.getId();
		instrumentDAO.deleteInstrument(id);
		instrum  = instrumentDAO.getInstrumentByID(id);
		assertTrue(instrum==null,"box must be null(deletet)");
	}
	/*@Test  dont run - damge test data
	void updateInstrument() {
		initConnection();
		for( int i=16;i<100;i++) {
			Instrument instrum = instrumentDAO.getInstrumentByID(i);
			instrum.setTotalNumber(instrum.getTotalNumber()+5);
			instrumentDAO.updateInstrument(instrum);
		}
		
	}
	*/	

}
