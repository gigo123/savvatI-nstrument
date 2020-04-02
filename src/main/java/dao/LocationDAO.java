package dao;

import java.util.List;

import models.Location;

public interface LocationDAO {
	public  boolean createLocation(Location location);
	public Location getLocById(int id);
	public Location getLocByName(String name);
	public List<Location> getLocByNameL(String name);
	public boolean deleteLocation(int id);
	public boolean hasError();
}
