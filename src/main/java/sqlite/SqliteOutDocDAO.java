package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OutDocDAO;
import models.OutDoc;

public class SqliteOutDocDAO implements OutDocDAO {
	private final static String SELECT_ID_QUERY = "SELECT * FROM outDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM outDoc WHERE outLocation = ?"
			+ "AND outBox = ? OR inBox = ?";
	private final static String SELECT_INST_QUERY = "SELECT * FROM outDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM outDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO outDoc( outLocation, outBox, date, instrument, amount)"
			+ " VALUES(?,?,?,?,?)";
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
	public boolean OutDoc(OutDoc outDoc) {
		Connection conn = conectionHolder.getConnection();
		PreparedStatement prepSt = null;
		if (!conectionHolder.isError()) {
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setInt(2, outDoc.getOutLocation().getId());
				prepSt.setInt(4, (int) outDoc.getOutBox().getId());
				prepSt.setDate(5, outDoc.getDate());
				prepSt.setInt(6, (int) outDoc.getInstrument().getId());
				prepSt.setFloat(7, outDoc.getAmount());
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
		PreparedStatement prepSt = null;
		SqliteLocationDAO locDao = new SqliteLocationDAO();
		SqliteInstrumentDAO instDao = new SqliteInstrumentDAO();
		SqliteBoxDAO boxDao = new SqliteBoxDAO();
		List<OutDoc> docList = new ArrayList<OutDoc>();
		OutDoc outdoc = new OutDoc();
		Connection conn = conectionHolder.getConnection();
		if (!conectionHolder.isError()) {
			try {
				switch (type) {
				case 1: {
					prepSt = conn.prepareStatement(SELECT_ID_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 2: {
					prepSt = conn.prepareStatement(SELECT_DATE_QUERY);
					prepSt.setDate(1, (Date) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 3: {
					prepSt = conn.prepareStatement(SELECT_INST_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_LOCATION_QUERY);
					prepSt.setInt(1, (int) obj);
					prepSt.setInt(2, (int) obj2);
					rs = prepSt.executeQuery();
					break;
				}

				default:
					sqlError = true;
				}

				while (rs.next()) {
					if (type == 1) {
						outdoc.setId(rs.getInt("id"));
						outdoc.setOutLocation(locDao.getLocById(rs.getInt("inLocation")));
						outdoc.setOutBox(boxDao.getBoxByID(rs.getInt("inBox")));
						outdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						outdoc.setDate(rs.getDate("date"));
						outdoc.setAmount(rs.getFloat("amount"));
						return outdoc;
					} else {
						outdoc = new OutDoc();
						outdoc.setId(rs.getInt("id"));
						outdoc.setOutLocation(locDao.getLocById(rs.getInt("inLocation")));
						outdoc.setOutBox(boxDao.getBoxByID(rs.getInt("inBox")));
						outdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						outdoc.setDate(rs.getDate("date"));
						outdoc.setAmount(rs.getFloat("amount"));
						docList.add(outdoc);
					}
				}
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
		return docList;
	}

	@Override
	public models.OutDoc getOutDocById(int id) {
		return (OutDoc) selectQ(id, null, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OutDoc> getOutDocByDate(String date) {
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
	public boolean deleteInDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
