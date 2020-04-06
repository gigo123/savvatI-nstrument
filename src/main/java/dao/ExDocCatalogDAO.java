package dao;

import java.time.LocalDate;
import java.util.List;

import models.ExDocCatalog;

public interface ExDocCatalogDAO {

	public boolean createExDocCatalog(ExDocCatalog exDoc);
	public ExDocCatalog getExDocCatalogById(long id);
	public List<ExDocCatalog> getExDocCatalogByDate(LocalDate date);
	public List<ExDocCatalog> getExDocCatalogByNumber(int number);
	public ExDocCatalog getExDocCatalogBySnumber(String numberString);
	public boolean deleteExDocCatalogDoc(long id);
	public boolean hasError();

}
