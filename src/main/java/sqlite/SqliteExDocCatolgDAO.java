package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ExDocCatalogDAO;
import models.ExDocCatalog;
import models.InDoc;

public class SqliteExDocCatolgDAO implements ExDocCatalogDAO{

	

	private final static String SELECT_ID_QUERY = "SELECT * FROM exdoccatalog WHERE id = ?";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM exdoccatalog WHERE date =?";

	private final static String SELECT_NUBMER_QUERY = "SELECT * FROM exdoccatalog WHERE number = ? ";
	private final static String SELECT_SNUBMER_QUERY = "SELECT * FROM exdoccatalog WHERE numberString = ? ";
	private final static String INSERT_QUERY = "INSERT INTO exdoccatalog(numberString, number, date)"
		 + " VALUES(?,?,?)";
	private final static String DELETE_QUERY = "DELETE FROM exdoccatalog WHERE id = ?";
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
	public boolean createExDocCatalog(ExDocCatalog exDoc) {
		sqlError = false;
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setString(1, exDoc.getNumberString());
				prepSt.setInt(2, exDoc.getNumber());
				Date exDate = java.sql.Date.valueOf(exDoc.getDate().toString());
				prepSt.setDate(3, exDate);
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
	public ExDocCatalog getExDocCatalogById(long id) {
		return (ExDocCatalog) selectQ(id, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExDocCatalog> getExDocCatalogByDate(LocalDate date) {
		return (List<ExDocCatalog>) selectQ(date, 2);
	}


	@Override
	public ExDocCatalog getExDocCatalogBySnumber(String numberString) {
		return (ExDocCatalog) selectQ(numberString, 3);
	}

	@Override
	public boolean deleteExDocCatalogDoc(long id) {
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
	
	private Object selectQ(Object obj, int type) {
		sqlError = false;
		if (conectionHolder!=null&&conectionHolder.getConnection()!=null) {
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
					prepSt = conn.prepareStatement(SELECT_NUBMER_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_SNUBMER_QUERY);
					prepSt.setString(1, (String) obj);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ExDocCatalog> getExDocCatalogByNumber(int number) {
		return (List<ExDocCatalog>) selectQ(number, 4);
	}

}
