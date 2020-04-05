package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.OutDocDAO;
import models.OutDoc;

public class SqliteOutDocDAO implements OutDocDAO {
	private final static String SELECT_ID_QUERY = "SELECT * FROM outDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM outDoc WHERE outLocation = ?"
			+ " AND outBox = ?";
	private final static String SELECT_INST_QUERY = "SELECT * FROM outDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM outDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO outDoc( outLocation, outBox, date, instrument, amount)"
			+ " VALUES(?,?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM outDoc WHERE id = ?";
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
	public boolean createOutDoc(OutDoc outDoc) {
		sqlError = false;
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setLong(1, outDoc.getOutLocation().getId());
				prepSt.setInt(2, (int) outDoc.getOutBox().getId());
				Date exDate = java.sql.Date.valueOf(outDoc.getDate().toString());
				prepSt.setDate(3, exDate);
				prepSt.setInt(4, (int) outDoc.getInstrument().getId());
				prepSt.setFloat(5, outDoc.getAmount());
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

	@SuppressWarnings("null")
	private Object selectQ(Object obj, Object obj2, int type) {
		ResultSet rs = null;
		
		sqlError = false;
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			SqliteLocationDAO locDao = new SqliteLocationDAO();
			SqliteInstrumentDAO instDao = new SqliteInstrumentDAO();
			SqliteBoxDAO boxDao = new SqliteBoxDAO();
			List<OutDoc> docList = new ArrayList<OutDoc>();
			OutDoc outdoc = null;
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
					Date outDate = java.sql.Date.valueOf(locDate.toString());
					prepSt.setDate(1, outDate);
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
					prepSt.setLong(2, (long) obj2);
					rs = prepSt.executeQuery();
					break;
				}

				default:
					sqlError = true;
				}

				while (rs.next()) {
					outdoc = new OutDoc();
					outdoc.setId(rs.getInt("id"));
					outdoc.setOutLocation(locDao.getLocById(rs.getInt("outLocation")));
					outdoc.setOutBox(boxDao.getBoxByID(rs.getInt("outBox")));
					outdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("instrument")));
					Date outDate = rs.getDate("date");
					outdoc.setDate(outDate.toLocalDate());
					outdoc.setAmount(rs.getFloat("amount"));
					if (type == 1) {
						break;
					} else {
						docList.add(outdoc);
					}
				}
				conectionHolder.closeConnection();
				if (type == 1) {
					return outdoc;
				}
				else {
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
	public models.OutDoc getOutDocById(long id) {
		return (OutDoc) selectQ(id, null, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OutDoc> getOutDocByDate(LocalDate date) {
		return (List<OutDoc>) selectQ(date, null, 2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OutDoc> getOutDocByInstrum(long id) {
		return (List<OutDoc>) selectQ(id, null, 3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OutDoc> getOutDocByBox(long idB, long idL) {
		return (List<OutDoc>) selectQ(idB, idL, 4);
	}

	@Override
	public boolean deleteOutDoc(long id) {
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
