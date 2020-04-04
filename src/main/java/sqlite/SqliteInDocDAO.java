package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dao.InDocDAO;
import models.InDoc;


public class SqliteInDocDAO implements InDocDAO{
	private final static String SELECT_ID_QUERY = "SELECT * FROM inDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM inDoc WHERE inLocation = ?"
			+ " AND inBox = ?";
	private final static String SELECT_INST_QUERY = "SELECT * FROM inDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM inDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO inDoc( inLocation, "
			+ "inBox, date, instrument, amount)" + " VALUES(?,?,?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM inDoc WHERE id = ?";
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
	public boolean createInDoc(InDoc inDoc) {
		sqlError = false;
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setLong(1, inDoc.getInLocation().getId());
				prepSt.setInt(2, (int) inDoc.getInBox().getId());
				Date exDate = java.sql.Date.valueOf(inDoc.getDate().toString());
				prepSt.setDate(3, exDate);
				prepSt.setInt(4, (int) inDoc.getInstrument().getId());
				prepSt.setFloat(5, inDoc.getAmount());
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
			List<InDoc> docList = new ArrayList<InDoc>();
			InDoc indoc = null;
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
					prepSt.setLong(2, (long) obj2);
					rs = prepSt.executeQuery();
					break;
				}

				default:
				{
					sqlError = true;
				}
				}
				while (rs.next()) {
					indoc = new InDoc();
					indoc.setId(rs.getInt("id"));
					indoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
					indoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
					indoc.setInstrument(instDao.getInstrumentByID(rs.getInt("instrument")));
					Date inDate = rs.getDate("date");
					indoc.setDate(inDate.toLocalDate());
					indoc.setAmount(rs.getFloat("amount"));
					if (type == 1) {
						break;
					} else {
						docList.add(indoc);
					}
				}
				conectionHolder.closeConnection();
				if (type == 1) {
					return indoc;
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
	public InDoc getInDocById(long id) {
		return (InDoc) selectQ(id, null, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InDoc> getInDocByDate(LocalDate date) {
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
	public boolean deleteInDoc(long id) {
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
