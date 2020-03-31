package dao;

public abstract class DaoFactory {
public DaoFactory() {
		
	} 
	public abstract BoxDAO getBoxDAO();
	public abstract ExDocDAO getExDocDAO();
	public abstract InDocDAO getinDocDAO();
	public abstract OutDocDAO OutExDocDAO();
	public abstract InstrumentDAO getInstrumentDAO();
	public abstract LocationDAO getLocationDAO();
	
}
