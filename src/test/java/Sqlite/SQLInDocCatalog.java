package Sqlite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.InDocCatalog;
import sqlite.SQLConectionHolder;
import sqlite.SqliteInDocCatalogDAO;

public class SQLInDocCatalog {
SqliteInDocCatalogDAO exDoccDAO;
	
	
	void initConnection() {
		SQLConectionHolder conectionHolder = new SQLConectionHolder();
		conectionHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		exDoccDAO = new SqliteInDocCatalogDAO();
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
	void createInDocCatalog() {
		LocalDate today = LocalDate.now();
		InDocCatalog docc =new InDocCatalog("1", 1,  today);
		initConnection();
		exDoccDAO.createInDocCatalog(docc);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getInDocCatalogById() {
		initConnection();
		InDocCatalog exdoc =exDoccDAO.getInDocCatalogById(1);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getInDocCatalogByDate() {
		initConnection();
		List<InDocCatalog> exDocList  = exDoccDAO.getInDocCatalogByDate(LocalDate.now());
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	@Test
	void getInDocCatalogBySname() {
		initConnection();
		InDocCatalog exdoc =exDoccDAO.getInDocCatalogBySnumber("1");
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exdoc!=null,"must not be null");
	}
	@Test
	void getExDocCataloByNumber() {
		initConnection();
		List<InDocCatalog> exDocList  = exDoccDAO.getInDocCatalogByNumber(1);
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(exDocList.size()!=0,"must not be 0");
	}
	
	@Test
	void deleteInDoc() {
		initConnection();
		List<InDocCatalog> exDocList  = exDoccDAO.getInDocCatalogByDate(LocalDate.now());
		boolean error = exDoccDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= exDocList.get(exDocList.size()-1).getId();
		exDoccDAO.deleteInDocCatalogDoc(id);
		InDocCatalog exdoc =exDoccDAO.getInDocCatalogById(id);
		assertTrue(exdoc==null,"doc must be null(deletet)");
	}
}
