package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import models.Location;
import sqlite.SQLConectionHolder;
import sqlite.SqliteLocationDAO;

class SQLLoocation {

	@Test
	void setConnection() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		SqliteLocationDAO locationDAO = new SqliteLocationDAO();
		locationDAO.setConectionHolder(conectHolder);
		SQLConectionHolder conectHolder2  = locationDAO.getConectionHolder();
		assertTrue(conectHolder2!=null,"connetion must not be null");
	}
	@Test
	void createLocation() {
		SqliteLocationDAO locationDAO = new SqliteLocationDAO();
		Location location = new Location();
		location.setName("test1");
		location.setBoxes(false);
		locationDAO.createLocation(location);
		boolean error = locationDAO.hasError();
		assertTrue(error,"must be error");
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		locationDAO.setConectionHolder(conectHolder);
		locationDAO.createLocation(location);
		error = locationDAO.hasError();
		 assertTrue(!error,"must be ok");
	}

}
