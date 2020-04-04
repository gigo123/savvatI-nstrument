package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dao.ExDocDAO;
import models.ExDoc;

public class SqliteExDocDAO implements ExDocDAO {

	private final static String SELECT_ID_QUERY = "SELECT * FROM exDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM exDoc WHERE (outLocation = ? OR inLocation = ?)"
			+ " AND (outBox = ? OR inBox = ?)";
	private final static String SELECT_INST_QUERY = "SELECT * FROM exDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM exDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO exDoc(outLocation, inLocation, outBox, "
			+ "inBox, date, instrument, amount)" + " VALUES(?,?,?,?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM exDoc WHERE id = ?";
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
	public boolean createExDoc(ExDoc exDoc) {
		sqlError = false;
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setLong(1, exDoc.getOutLocation().getId());
				prepSt.setLong(2, exDoc.getInLocation().getId());
				prepSt.setInt(3, (int) exDoc.getOutBox().getId());
				prepSt.setInt(4, (int) exDoc.getInBox().getId());
				Date exDate = java.sql.Date.valueOf(exDoc.getDate().toString());
				prepSt.setDate(5, exDate);
				prepSt.setInt(6, (int) exDoc.getInstrument().getId());
				prepSt.setFloat(7, exDoc.getAmount());
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

	private Object selectQ(Object obj, Object obj2, int type) {
		sqlError = false;
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			ResultSet rs = null;
			PreparedStatement prepSt = null;
			SqliteLocationDAO locDao = new SqliteLocationDAO();
			SqliteInstrumentDAO instDao = new SqliteInstrumentDAO();
			SqliteBoxDAO boxDao = new SqliteBoxDAO();
			List<ExDoc> docList = new ArrayList<ExDoc>();
			ExDoc exdoc = null;
			try {
				switch (type) {
				case 1: {
					prepSt = conn.prepareStatement(SELECT_ID_QUERY);
					prepSt.setLong(1, (long) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 2: {
					prepSt = conn.prepareStatement(SELECT_DATE_QUERY);
					LocalDate locDate= (LocalDate) obj;
					Date exDate = java.sql.Date.valueOf(locDate.toString());
					prepSt.setDate(1, exDate);
					rs = prepSt.executeQuery();
					break;
				}
				case 3: {
					prepSt = conn.prepareStatement(SELECT_INST_QUERY);
					prepSt.setLong(1, (long) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_LOCATION_QUERY);
					prepSt.setLong(1, (long) obj);
					prepSt.setLong(2, (long) obj);
					prepSt.setLong(3, (long) obj2);
					prepSt.setLong(4, (long) obj2);
					rs = prepSt.executeQuery();
					break;
				}

				default: {
					sqlError = true;
				}
				}
				while (rs.next()) {
					exdoc = new ExDoc();
					exdoc.setId(rs.getInt("id"));
					exdoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
					exdoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
					exdoc.setOutBox(boxDao.getBoxByID(rs.getInt("outBox")));
					exdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("instrument")));
					Date exDate = rs.getDate("date");
					exdoc.setDate(exDate.toLocalDate());
					exdoc.setAmount(rs.getFloat("amount"));
					exdoc.setOutLocation(locDao.getLocById(rs.getInt("outLocation")));
					if (type == 1) {
						break;
					} else {
						docList.add(exdoc);
					}
				}
				conectionHolder.closeConnection();
				if (type == 1) {
					return exdoc;
				} else {
					return docList;
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

	@Override
	public ExDoc getExDocById(long id) {
		return (ExDoc) selectQ(id, null, 1);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExDoc> getExDocByDate(LocalDate date) {
		return (List<ExDoc>) selectQ(date, null, 2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExDoc> getExDocByInstrum(long id) {
		return (List<ExDoc>) selectQ(id, null, 3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExDoc> getExDocByBox(long idB, long idL) {
		return (List<ExDoc>) selectQ(idB, idL, 4);
	}

	@Override
	public boolean deleteExDoc(long id) {
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
