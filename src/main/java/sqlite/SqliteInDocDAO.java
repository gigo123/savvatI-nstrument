package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.InDocDAO;
import models.Box;
import models.InDoc;
import models.Instrument;
import models.Location;

public class SqliteInDocDAO implements InDocDAO{
	private final static String SELECT_ID_QUERY = "SELECT * FROM inDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM inDoc WHERE inLocation = ?"
			+ "AND outBox = ? OR inBox = ?";
	private final static String SELECT_INST_QUERY = "SELECT * FROM inDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM inDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO inDoc( inLocation, "
			+ "inBox, date, instrument, amount)" + " VALUES(?,?,?,?,?)";
	private SQLConectionHolder conectionHolder;
	private boolean sqlError = false;

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
	public boolean createInDoc(InDoc inDoc) {
		Connection conn = conectionHolder.getConnection();
		PreparedStatement prepSt = null;
		if (!conectionHolder.isError()) {
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setInt(2, inDoc.getInLocation().getId());
				prepSt.setInt(4, (int) inDoc.getInBox().getId());
				prepSt.setDate(5, inDoc.getDate());
				prepSt.setInt(6, (int) inDoc.getInstrument().getId());
				prepSt.setFloat(7, inDoc.getAmount());
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
		List<InDoc> docList = new ArrayList<InDoc>();
		InDoc indoc = new InDoc();
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
						indoc.setId(rs.getInt("id"));
						indoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
						indoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
						indoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						indoc.setDate(rs.getDate("date"));
						indoc.setAmount(rs.getFloat("amount"));
						return indoc;
					} else {
						indoc = new InDoc();
						indoc.setId(rs.getInt("id"));
						indoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
						indoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
						indoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						indoc.setDate(rs.getDate("date"));
						indoc.setAmount(rs.getFloat("amount"));
						docList.add(indoc);
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
	public InDoc getInDocById(int id) {
		return (InDoc) selectQ(id, null, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InDoc> getInDocByDate(String date) {
		return (List<InDoc> ) selectQ(date, null, 2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InDoc> getInDocByInstrum(long id) {
		return (List<InDoc> ) selectQ(id, null, 3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InDoc> getInDocByBox(long idB,long idL) {
		return (List<InDoc> ) selectQ(idB, idL, 4);
	}

	@Override
	public boolean deleteInDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
