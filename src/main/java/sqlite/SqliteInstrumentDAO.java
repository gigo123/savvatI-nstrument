package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.InstrumentDAO;
import models.Box;
import models.Instrument;
import models.Location;
import savvats.InstrumentWNum;

public class SqliteInstrumentDAO implements InstrumentDAO {

	private final static String SELECT_ID_QUERY = "SELECT * FROM instrument WHERE id = ?";
	private final static String SELECT_NAME_QUERY = "SELECT * FROM instrument WHERE NAME = ?";
	private final static String SELECT_BOX_QUERY = "SELECT * FROM box WHERE  box = ? AND loaction = ?";
	private final static String SELECT_LOC_QUERY = "SELECT * FROM box WHERE  loaction = ?";
	private final static String SELECT_ALL_QUERY = "SELECT * FROM box";
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

	@SuppressWarnings("null")
	private Object selectQ(Object obj,Object obj2, int type) {

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Instrument inst = null;
		SqliteLocationDAO locDao =new SqliteLocationDAO();
		List<Box> boxList =new ArrayList<Box>();
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
					prepSt = conn.prepareStatement(SELECT_NAME_QUERY);
					prepSt.setString(1, (String) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 3: {
					prepSt = conn.prepareStatement(SELECT_NAME_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_BOX_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 5: {
					prepSt = conn.prepareStatement(SELECT_LOC_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 6: {
					rs= prepSt.executeQuery(SELECT_ALL_QUERY);
					break;
				}
				default:
					sqlError = true;
				}

				while (rs.next()) {
					if (type == 1 || type == 2) {
						inst.setId(rs.getInt("id"));
						inst.setName(rs.getString("name"));
						inst.setComment(rs.getString("comment"));
						inst.setMeasure(rs.getString("measure"));
						return inst;
					} else {
					
						Box box =new Box();
						box.setId(rs.getInt("id"));
						box.setInstruments(getInstrumentByID(rs.getInt("instruments")));
						box.setInstrumentsNumbers(rs.getFloat("instrumentsNumbers"));
						box.setLocation(locDao.getLocById(rs.getInt("location")));
						box.setNumber(rs.getInt("number"));
						boxList.add(box);
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
		return boxList;

	}

	@Override
	public Instrument getInstrumentByID(long id) {
		return (Instrument)selectQ(id,null,1);
	}

	@Override
	public Instrument getInstrumentByName(String name) {
			return (Instrument)selectQ(name,null,2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getInstrumentByNameL(String name) {
		return (List<Box>)selectQ(name,null,3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getInstrumentByBox(long idB, int idL) {
		return (List<Box>)selectQ(idB,idL,4);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getInstrumentByLocation(Location loacation) {
		return (List<Box>)selectQ(loacation,null,5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Box> getAllInstrument() {
		return (List<Box>)selectQ(null,null,6);
	}

	@Override
	public boolean createInstrument(String name, String measure, String comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createInstrument(String name, String measure) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteInstrument(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
