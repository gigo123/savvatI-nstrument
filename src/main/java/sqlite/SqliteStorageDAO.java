package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BoxDAO;
import dao.InstrumentDAO;
import dao.StorageDAO;
import models.Box;
import models.Instrument;
import models.Storage;

public class SqliteStorageDAO  implements StorageDAO{
private final static String SELECT_ID_QUERY = "SELECT * FROM storage WHERE id = ?";
private final static String SELECT_BOX_QUERY = "SELECT * FROM storage WHERE box = ?";
private final static String SELECT_INSTR_QUERY = "SELECT * FROM storage WHERE instrument = ?";
private final static String INSERT_QUERY = "INSERT INTO storage(box, instrument, amount)" + " VALUES(?, ?, ?)";
private final static String DELETE_QUERY = "DELETE FROM storage WHERE id = ?";
private SQLConectionHolder conectionHolder;
private boolean sqlError = false;

	@Override
	public Storage getStorageByID(long id) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Storage storage = null;
		BoxDAO boxDAO = new SqliteBoxDAO();
		InstrumentDAO instDAO = new SqliteInstrumentDAO();
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
		try {
			prepSt = conn.prepareStatement(SELECT_ID_QUERY);
			prepSt.setLong(1, (long)id);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				storage= new Storage();
				storage.setId(rs.getLong("id"));
				storage.setBox(boxDAO.getBoxByID(rs.getLong("box")));
				storage.setInstrument(instDAO.getInstrumentByID(rs.getLong("instrument")));
				storage.setAmount(rs.getFloat("amount"));
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
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
		}
		else {
			sqlError=true;
		}
		return storage;
	}

	@Override
	public Storage getStorageByinstrument(Instrument instrument) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Storage storage = null;
		BoxDAO boxDAO = new SqliteBoxDAO();
		InstrumentDAO instDAO = new SqliteInstrumentDAO();
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
		try {
			prepSt = conn.prepareStatement(SELECT_INSTR_QUERY);
			prepSt.setLong(1, (long)instrument.getId());
			rs = prepSt.executeQuery();

			while (rs.next()) {
				storage= new Storage();
				storage.setId(rs.getLong("id"));
				storage.setBox(boxDAO.getBoxByID(rs.getLong("box")));
				storage.setInstrument(instDAO.getInstrumentByID(rs.getLong("instrument")));
				storage.setAmount(rs.getFloat("amount"));
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
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
		}
		else {
			sqlError=true;
		}
		return storage;
	}

	@Override
	public Storage getStorageByBox(Box box) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		Storage storage = null;
		BoxDAO boxDAO = new SqliteBoxDAO();
		InstrumentDAO instDAO = new SqliteInstrumentDAO();
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
		try {
			prepSt = conn.prepareStatement(SELECT_BOX_QUERY);
			prepSt.setLong(1, (long)box.getId());
			rs = prepSt.executeQuery();

			while (rs.next()) {
				storage= new Storage();
				storage.setId(rs.getLong("id"));
				storage.setBox(boxDAO.getBoxByID(rs.getLong("box")));
				storage.setInstrument(instDAO.getInstrumentByID(rs.getLong("instrument")));
				storage.setAmount(rs.getFloat("amount"));
			}
			conectionHolder.closeConnection();
		} catch (SQLException e) {
			sqlError=true;
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
		}
		else {
			sqlError=true;
		}
		return storage;
	}

	@Override
	public boolean createStorage(Storage storage) {
		if (conectionHolder != null && !conectionHolder.isError()) {
			Connection conn = conectionHolder.getConnection();
			PreparedStatement prepSt = null;
			try {
				prepSt = conn.prepareStatement(INSERT_QUERY);
				prepSt.setLong(1, storage.getBox().getId());
				prepSt.setLong(2, storage.getInstrument().getId());
				prepSt.setFloat(3, storage.getAmount());
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
	public boolean deleteStorage(long id) {
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

}
