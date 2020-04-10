package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BoxDAO;
import models.Box;

public class SqliteBoxDAO implements BoxDAO {
	private final static String SELECT_ID_QUERY = "SELECT * FROM box WHERE id = ?";
	private final static String SELECT_NUMBER_QUERY = "SELECT * FROM box WHERE number = ? AND location =?";
	private final static String INSERT_QUERY = "INSERT INTO box(number, location)" + " VALUES(?, ?)";
	private final static String DELETE_QUERY = "DELETE FROM box WHERE id = ?";
	private final static String SELECT_ALL = "SELECT * FROM box";
	private final static String SELECT_ALL_LOCATION = "SELECT * FROM box WHERE location = ?";
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
		return (Box)selectQ(id,null,1);
	}

	@Override
	public Box getBoxByNumber(int number, long idLocation) {
		return (Box)selectQ(number,idLocation,2);
	}

	@Override
	public boolean createBox(Box box) {
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setInt(1, box.getNumber());
				prepSt.setLong(2, box.getLocation().getId());
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
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getAllBox() {
		return (List<Box>)selectQ(null,null,3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getAllBoxByLocation(long idLocation) {
		
		return (List<Box>)selectQ(idLocation,null,4);
	}

	private Object selectQ(Object obj, Object obj2, int type) {
		sqlError = false;
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
			Connection conn = conectionHolder.getConnection();
			ResultSet rs = null;
			PreparedStatement prepSt = null;
			SqliteLocationDAO locDao = new SqliteLocationDAO();
			locDao.setConectionHolder(conectionHolder);
			List<Box> boxList = new ArrayList<Box>();
			Box box = null;
			try {
				switch (type) {
				case 1: {
					prepSt = conn.prepareStatement(SELECT_ID_QUERY);
					prepSt.setLong(1, (long) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 2: {
					prepSt = conn.prepareStatement(SELECT_NUMBER_QUERY);
					prepSt.setInt(1, (int)obj);
					prepSt.setLong(2, (long)obj2);
					rs = prepSt.executeQuery();
					break;
				}
				case 3: {
					prepSt = conn.prepareStatement(SELECT_ALL);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_ALL_LOCATION);
					prepSt.setLong(1, (long) obj);
					rs = prepSt.executeQuery();
					break;
				}

				default: {
					sqlError = true;
				}
				}
				while (rs.next()) {
					box = new Box();
					box.setId(rs.getInt("id"));
					box.setLocation(locDao.getLocById(rs.getInt("location")));
					box.setNumber(rs.getInt("number"));
					if (type == 1||type ==2) {
						break;
					} else {
						boxList.add(box);
					}
				}
				if (type == 1||type ==2) {
					return box;
				} else {
					return boxList;
				}

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
		return null;

	}
}
