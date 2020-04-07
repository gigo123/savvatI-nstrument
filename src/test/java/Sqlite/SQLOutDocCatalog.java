package Sqlite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.OutDocCatalog;
import sqlite.SQLConectionHolder;
import sqlite.SqliteOutDocCatalogDAO;

public class SQLOutDocCatalog {
SqliteOutDocCatalogDAO exDoccDAO;
	
	
	void initConnection() {
		SQLConectionHolder conectionHolder = new SQLConectionHolder();
		conectionHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		exDoccDAO = new SqliteOutDocCatalogDAO();
		exDoccDAO.setConectionHolder(conectionHolder);
	}
	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = exDoccDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createOutDocCatalog() {
		LocalDate today = LocalDate.now();
		OutDocCatalog docc =new OutDocCatalog("1", 1,  today);
		initConnection();
		exDoccDAO.createOutDocCatalog(docc);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getOutDocCatalogById() {
		initConnection();
		OutDocCatalog exdoc =exDoccDAO.getOutDocCatalogById(1);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getOutDocCatalogByDate() {
		initConnection();
		List<OutDocCatalog> exDocList  = exDoccDAO.getOutDocCatalogByDate(LocalDate.now());
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getOutDocCatalogBySname() {
		initConnection();
		OutDocCatalog exdoc =exDoccDAO.getOutDocCatalogBySnumber("1");
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getOutDocCatalogByNumber() {
		initConnection();
		List<OutDocCatalog> exDocList  = exDoccDAO.getOutDocCatalogByNumber(1);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	
	@Test
	void deleteInDoc() {
		initConnection();
		List<OutDocCatalog> exDocList  = exDoccDAO.getOutDocCatalogByDate(LocalDate.now());
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= exDocList.get(exDocList.size()-1).getId();
		exDoccDAO.deleteOutDocCatalogDoc(id);
		OutDocCatalog exdoc =exDoccDAO.getOutDocCatalogById(id);
		assertTrue(exdoc==null,"doc must be null(deletet)");
	}
}
