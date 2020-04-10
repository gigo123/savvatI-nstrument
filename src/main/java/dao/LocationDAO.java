package dao;

import java.util.List;

import models.Location;

public interface LocationDAO {
	public  boolean createLocation(Location location);
	public Location getLocById(long id);
	public Location getLocByName(String name);
	public List<Location> getAllLocatin();
	public List<Location> getAllLocatinWB();
	public boolean deleteLocation(long id);
	public boolean hasError();
	public void closeConection();
}
