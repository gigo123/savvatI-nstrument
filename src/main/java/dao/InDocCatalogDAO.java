package dao;

import java.time.LocalDate;
import java.util.List;
import models.InDocCatalog;

public interface InDocCatalogDAO {
	
	public boolean createInDocCatalog(InDocCatalog exDoc);
	public InDocCatalog getInDocCatalogById(long id);
	public List<InDocCatalog> getInDocCatalogByDate(LocalDate date);
	public List<InDocCatalog> getInDocCatalogByNumber(int number);
	public InDocCatalog getInDocCatalogBySnumber(String numberString);
	public boolean deleteInDocCatalogDoc(long id);
	public boolean hasError();
}
