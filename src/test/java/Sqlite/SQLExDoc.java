package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;
import models.Box;
import models.DocModel;
import models.ExDoc;
import models.ExDocCatalog;
import models.Instrument;
import models.Location;
import savvats.DocType;
import sqlite.SQLConectionHolder;
import sqlite.SqliteBoxDAO;
import sqlite.SqliteExDocCatalogDAO;
import sqlite.SqliteExDocDAO;
import sqlite.SqliteInstrumentDAO;
import sqlite.SqliteLocationDAO;

class SQLExDoc {

	SqliteBoxDAO boxDAO;
	SqliteLocationDAO locationDAO;
	SqliteInstrumentDAO instrumentDAO;
	SqliteExDocDAO exDocDAO;
	SqliteExDocCatalogDAO exDocCatalogDao;
	
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
		exDocCatalogDao = new SqliteExDocCatalogDAO();
		exDocCatalogDao.setConectionHolder(conectionHolder);
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
		Instrument inst = instrumentDAO.getInstrumentByID(4);
		Location location =  locationDAO.getLocById(1);
		Box box = boxDAO.getBoxByID(11);
		ExDocCatalog docCat= exDocCatalogDao.getExDocCatalogById(3);
		ExDoc exDoc = new ExDoc(location, box, location,box, docCat, inst, 1);
		exDocDAOc.createExDoc(exDoc,DocType.EXDOC);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		exDocDAO.createExDoc(exDoc,DocType.EXDOC);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getExDocById() {
		SqliteExDocDAO  exDocDAOc = new SqliteExDocDAO();
		exDocDAOc.getExDocById(1,DocType.EXDOC);
		boolean error = exDocDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		ExDoc exdoc =exDocDAO.getExDocById(1,DocType.EXDOC);
		error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getExDocByBox() {
		initConnection();
		List<DocModel> exDocList  = exDocDAO.getExDocByBox(9,DocType.EXDOC);
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getExDocByInstrument() {
		initConnection();
		List<DocModel> exDocList  = exDocDAO.getExDocByInstrum(instrumentDAO.getInstrumentByID(2).getId(),DocType.EXDOC);
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getExDocByLocation() {
		initConnection();
		List<DocModel> exDocList  = exDocDAO.getExDocByLocation(1,DocType.EXDOC);
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
/*	
	@Test
	void deleteExDoc() {
		initConnection();
		List<ExDoc> exDocList  = exDocDAO.getExDocByDate(LocalDate.now());
		boolean error = exDocDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= exDocList.get(exDocList.size()-1).getId();
		exDocDAO.deleteExDoc(id);
		ExDoc exdoc =exDocDAO.getExDocById(id);
		assertTrue(exdoc==null,"doc must be null(deletet)");
	}
	*/
}
