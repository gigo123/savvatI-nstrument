package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocationDAO;
import models.Location;

public class SqliteLocationDAO implements LocationDAO {
	private final static String SELECT_ID_QUERY = "SELECT * FROM location WHERE id = ?";
	private final static String SELECT_NAME_QUERY = "SELECT * FROM location WHERE NAME = ?";
	private SQLConectionHolder conectionHolder;
	private boolean sqlError= false;
	public boolean isSqlError() {
		return sqlError;
	}

	public void setSqlError(boolean sqlError) {
		this.sqlError = sqlError;
	}

	public SQLConectionHolder getConectionHolder() {
		return conectionHolder;
	}

	public void setConectionHolder(SQLConectionHolder conectionHolder) {
		this.conectionHolder = conectionHolder;
	}

	@Override
	public boolean createLocation(String name, boolean boxes) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("null")
	@Override
	public Location getLocById(int id) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Location loc = new Location();
		Connection conn = conectionHolder.getConnection();
		if(!conectionHolder.isError()){
		try {
			prepSt = conn.prepareStatement(SELECT_ID_QUERY);
			prepSt.setInt(1, id);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				loc.setId(rs.getInt("id"));
				loc.setName(rs.getString("name"));
				loc.setBoxes(rs.getBoolean("boxes"));
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
		}
		else {
			sqlError=true;
		}
		return loc;
	}

	@SuppressWarnings("null")
	@Override
	public Location getLocByName(String name) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Location loc = null;
		Connection conn = conectionHolder.getConnection();
		if(!conectionHolder.isError()){
		try {
			prepSt = conn.prepareStatement(SELECT_NAME_QUERY);
			prepSt.setString(1, name);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				loc.setId(rs.getInt("id"));
				loc.setName(rs.getString("name"));
				loc.setBoxes(rs.getBoolean("boxes"));
				break;
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
		}
		else {
			sqlError=true;
		}
		return loc;
	}

	@SuppressWarnings("null")
	@Override
	public List<Location> getLocByNameL(String name) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		List<Location> locList =new ArrayList<Location>() ;
		Location loc = null;
		Connection conn = conectionHolder.getConnection();
		if(!conectionHolder.isError()){
		try {
			prepSt = conn.prepareStatement(SELECT_NAME_QUERY);
			prepSt.setString(1, name);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				loc = null;
				loc.setId(rs.getInt("id"));
				loc.setName(rs.getString("name"));
				loc.setBoxes(rs.getBoolean("boxes"));
				locList.add(loc);
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
		}
		else {
			sqlError=true;
		}
		return locList;
	}

	@Override
	public boolean deleteLocation(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
