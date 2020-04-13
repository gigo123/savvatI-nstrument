package sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DocCatalogDAO;
import models.DocCatalog;
import models.ExDocCatalog;
import models.OutDocCatalog;

public class SqliteOutDocCatalogDAO  implements DocCatalogDAO{

	private final static String SELECT_ID_QUERY = "SELECT * FROM outdoccatalog WHERE id = ?";
	private final static String SELECT_DATE_QUERY = "SELECT * FROM outdoccatalog WHERE date =?";
	private final static String SELECT_NUBMER_QUERY = "SELECT * FROM outdoccatalog WHERE number = ? ";
	private final static String SELECT_SNUBMER_QUERY = "SELECT * FROM outdoccatalog WHERE numberString = ? ";
	private final static String INSERT_QUERY = "INSERT INTO outdoccatalog(numberString, number, date)"
			+ " VALUES(?,?,?)";
	
	private final static String SELECT_YEAR_QUERY = "SELECT * FROM outdoccatalog WHERE year = ? ";
	private final static String SELECT_YEAR_N_QUERY = "SELECT number FROM outdoccatalog WHERE year = ? ";
	private final static String DELETE_QUERY = "DELETE FROM outdoccatalog WHERE id = ?";
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
	public boolean createExDocCatalog(DocCatalog exDoc) {
		sqlError = false;
		if (conectionHolder != null && conectionHolder.getConnection() != null) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setString(1, exDoc.getNumberString());
				prepSt.setInt(2, exDoc.getNumber());
				prepSt.setInt(3, exDoc.getYear());
				Date exDate = java.sql.Date.valueOf(exDoc.getDate().toString());
				prepSt.setDate(4, exDate);
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
	public boolean deleteExDocCatalogDoc(long id) {
		sqlError = false;
		PreparedStatement prepSt = null;
		if (conectionHolder != null && !conectionHolder.isError()) {
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
		if (conectionHolder != null && conectionHolder.getConnection() != null) {
			Connection conn = conectionHolder.getConnection();
			ResultSet rs = null;
			PreparedStatement prepSt = null;
			DocCatalog exDoc = null;
			List<DocCatalog> docList = new ArrayList<DocCatalog>();
			List<Integer> numberList = new ArrayList<Integer>();
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
					LocalDate locDate = (LocalDate) obj;
					Date exDate = java.sql.Date.valueOf(locDate.toString());
					prepSt.setDate(1, exDate);
					rs = prepSt.executeQuery();
					break;
				}
				case 3: {
					prepSt = conn.prepareStatement(SELECT_SNUBMER_QUERY);
					prepSt.setString(1, (String) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 4: {
					prepSt = conn.prepareStatement(SELECT_NUBMER_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 5: {
					prepSt = conn.prepareStatement(SELECT_YEAR_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}
				case 6: {
					prepSt = conn.prepareStatement(SELECT_YEAR_N_QUERY);
					prepSt.setInt(1, (int) obj);
					rs = prepSt.executeQuery();
					break;
				}

				default: {
					sqlError = true;
				}
				}
				while (rs.next()) {
					if (type == 6) {
						numberList.add(rs.getInt("number"));
					} else {

						exDoc = new ExDocCatalog();
						exDoc.setId(rs.getInt("id"));
						exDoc.setNumberString(rs.getString("numberString"));
						exDoc.setNumber(rs.getInt("number"));
						Date inDate = rs.getDate("date");
						exDoc.setDate(inDate.toLocalDate());
						if (type == 1 || type == 3) {
							break;
						} else {
							docList.add(exDoc);
						}
					}
				}
				if (type == 1 || type == 3) {
					return exDoc;
				}
				if (type == 6) {
					return numberList;
				}
				return docList;
			} catch (SQLException e) {
				sqlError = true;
				e.printStackTrace();
				System.out.println(e);
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
	public ExDocCatalog getExDocCatalogById(long id) {
		return (ExDocCatalog) selectQ(id, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocCatalog> getExDocCatalogByDate(LocalDate date) {
		return (List<DocCatalog>) selectQ(date, 2);
	}

	@Override
	public DocCatalog getExDocCatalogBySnumber(String numberString) {
		return (DocCatalog) selectQ(numberString, 3);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DocCatalog> getExDocCatalogByNumber(int number) {
		return (List<DocCatalog>) selectQ(number, 4);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocCatalog> getExDocCatalogByYear(int year) {
		return (List<DocCatalog>) selectQ(year, 5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getExDocCatalogByYearN(int year) {
		return (List<Integer>) selectQ(year, 6);
	}
	@Override
	public void closeConection() {
		conectionHolder.closeConnection();
		
	}

}
