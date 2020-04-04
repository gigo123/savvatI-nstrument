package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BoxDAO;
import models.Box;
import models.Instrument;

public class SqliteBoxDAO implements BoxDAO{

	private final static String SELECT_ID_QUERY = "SELECT * FROM box WHERE id = ?";
	private final static String SELECT_NUMBER_QUERY = "SELECT * FROM box WHERE number = ? AND location =?";
	private final static String INSERT_QUERY = "INSERT INTO box(number, location)" + " VALUES(?, ?)";
	private final static String DELETE_QUERY = "DELETE FROM box WHERE id = ?";
	private SQLConectionHolder conectionHolder;
	private boolean sqlError = false;

	@Override
	public boolean hasError() {
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
	public Box getBoxByID(long id) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Box box = null;
		SqliteLocationDAO locDao = new SqliteLocationDAO();
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
		try {
			prepSt = conn.prepareStatement(SELECT_ID_QUERY);
			prepSt.setInt(1, (int)id);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				box= new Box();
				box.setId(rs.getInt("id"));
				box.setLocation(locDao.getLocById(rs.getInt("location")));
				box.setNumber(rs.getInt("number"));
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
		return box;
	}


	@Override
	public Box getBoxByNumber(int number, int idLocation) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Box box = new Box();
		SqliteLocationDAO locDao = new SqliteLocationDAO();
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
		try {
			prepSt = conn.prepareStatement(SELECT_NUMBER_QUERY);
			prepSt.setInt(1, number);
			prepSt.setInt(2, idLocation);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				box.setId(rs.getInt("id"));
				box.setLocation(locDao.getLocById(rs.getInt("location")));
				box.setNumber(rs.getInt("number"));
				return box;
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
		return box;
	}

	@Override
	public boolean createBox(Box box) {
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setInt(1, box.getNumber());
				prepSt.setInt(2, box.getLocation().getId());
				prepSt.execute();
				conectionHolder.closeConnection();
			} catch (SQLException e) {
				sqlError = true;
				e.printStackTrace();
				return false;
			} finally {
				if (prepSt != null) {
					try {
						prepSt.close();
					} catch (SQLException sqlEx) {
					}
					prepSt = null;
				}
			}
			return true;
		} else {
			sqlError = true;
			return false;
		}
	}

	@Override
	public boolean deleteBox(long id) {
		sqlError = false;
		PreparedStatement prepSt = null;
		if ( conectionHolder!=null&&!conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			try {
				prepSt = conn.prepareStatement(DELETE_QUERY);
				prepSt.setLong(1, id);
				prepSt.execute();
				conectionHolder.closeConnection();
			} catch (SQLException e) {
				sqlError = true;
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
		} else {
			sqlError = true;
		}
		return false;
	}

}
