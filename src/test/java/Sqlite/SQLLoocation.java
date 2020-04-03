package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteLocationDAO;

class SQLLoocation {
	SqliteLocationDAO locationDAO;
	
	void initConnection() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectHolder);
	}

	@Test
	void setConnection() {
		initConnection();
		SQLConectionHolder conectHolder2  = locationDAO.getConectionHolder();
		Connection conn =conectHolder2.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
	}
	@Test
	void createLocation() {
		SqliteLocationDAO locationDAOc = new SqliteLocationDAO();
		Location location = new Location();
		location.setName("test1");
		location.setBoxes(false);
		locationDAOc.createLocation(location);
		boolean error = locationDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		locationDAO.createLocation(location);
		error = locationDAO.hasError();
		assertTrue(!error,"must be ok");
	}
	@Test
	void getLocById() {
		SqliteLocationDAO locationDAOc = new SqliteLocationDAO();
		locationDAOc.getLocById(1);
		boolean error = locationDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Location location = locationDAO.getLocById(1);
		error = locationDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(location!=null,"must not be null");
	}
	@Test
	void getLocByName() {
		SqliteLocationDAO locationDAOc = new SqliteLocationDAO();
		locationDAOc.getLocByName("test1");
		boolean error = locationDAOc.hasError();
		assertTrue(error,"must be error");
		initConnection();
		Location location = locationDAO.getLocByName("test1");
		error = locationDAO.hasError();
		assertTrue(!error,"must be ok");
		assertTrue(location.getName().equals("test1"),"must be test1");
		assertTrue(!location.isBoxes(),"must be false");
	}
	@Test
	void deleteLocation() {
		initConnection();
		Location location = locationDAO.getLocByName("test1");
		boolean error = locationDAO.hasError();
		assertTrue(!error,"must be ok");
		long id= location.getId();
		locationDAO.deleteLocation(id);
		location  = locationDAO.getLocById(id);
		assertTrue(location == null,"box must be null(deletet)");
	}

}
