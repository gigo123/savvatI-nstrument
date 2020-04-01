package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ExDocDAO;
import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public class SqliteExDocDAO implements ExDocDAO {

	private final static String SELECT_ID_QUERY = "SELECT * FROM exDoc WHERE id = ?";
	private final static String SELECT_LOCATION_QUERY = "SELECT * FROM exDoc WHERE outLocation = ? OR inLocation = ?"+
	"AND outBox = ? OR inBox = ?";
	private final static String SELECT_INST_QUERY = "SELECT * FROM exDoc WHERE instrument = ? ";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM exDoc WHERE date = ?";
	private final static String INSERT_QUERY = "INSERT INTO location(name, boxes, comment)" + " VALUES(?, ?)";
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
	public boolean createExDoc(ExDoc exDoc) {
	
		return false;
	}

	@SuppressWarnings("null")
	private Object selectQ(Object obj, Object obj2, int type) {

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		SqliteLocationDAO locDao = new SqliteLocationDAO();
		SqliteInstrumentDAO instDao = new SqliteInstrumentDAO();
		SqliteBoxDAO boxDao = new SqliteBoxDAO();
		List<ExDoc> docList = new ArrayList<ExDoc>();
		ExDoc exdoc= new ExDoc();
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
					prepSt = conn.prepareStatement(SELECT_LOCATION_QUERY);
					prepSt.setString(1, (String) obj);
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
					prepSt = conn.prepareStatement(SELECT_DATE_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
			
				default:
					sqlError = true;
				}

				while (rs.next()) {
					if (type == 1) {
						exdoc.setId(rs.getInt("id"));
						exdoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
						exdoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
						exdoc.setOutBox(boxDao.getBoxByID(rs.getInt("outBox")));
						exdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						exdoc.setDate(rs.getDate("date"));
						exdoc.setAmount(rs.getFloat("amount"));
						exdoc.setOutLocation(locDao.getLocById(rs.getInt("outLocation")));
						return exdoc;
					} else {
						exdoc= new ExDoc();
						exdoc.setId(rs.getInt("id"));
						exdoc.setInLocation(locDao.getLocById(rs.getInt("inLocation")));
						exdoc.setInBox(boxDao.getBoxByID(rs.getInt("inBox")));
						exdoc.setOutBox(boxDao.getBoxByID(rs.getInt("outBox")));
						exdoc.setInstrument(instDao.getInstrumentByID(rs.getInt("istrument")));
						exdoc.setDate(rs.getDate("date"));
						exdoc.setAmount(rs.getFloat("amount"));
						exdoc.setOutLocation(locDao.getLocById(rs.getInt("outLocation")));
						docList.add(exdoc);
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
	public ExDoc getExDocById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByInstrum(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExDoc> getExDocByBox(long idB, long idL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteExDoc(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
