package Sqlite;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import sqlite.SQLConectionHolder;

class SqlHolder {

	@Test
	void test() {
		SQLConectionHolder conectHolder = new SQLConectionHolder();
		conectHolder.setConnString("jdbc:mysql://localhost/instrument1?user=root&password=");
		Connection conn = conectHolder.getConnection();
		assertTrue(conn!=null,"connetion must not be null");
		//conectHolder.closeConnection();
		//assertTrue(conn==null,"connetion must  be null after close");
	}

}
