package dao;

import java.time.LocalDate;
import java.util.List;

import models.OutDocCatalog;

public interface OutDocCatalogDAO {

	public boolean createOutDocCatalog(OutDocCatalog exDoc);
	public OutDocCatalog getOutDocCatalogById(long id);
	public List<OutDocCatalog> getOutDocCatalogByDate(LocalDate date);
	public List<OutDocCatalog> getOutDocCatalogByNumber(int number);
	public OutDocCatalog getOutDocCatalogBySnumber(String numberString);
	public boolean deleteOutDocCatalogDoc(long id);
	public boolean hasError();
}
