package dao;

import java.time.LocalDate;
import java.util.List;
import models.InDocCatalog;

interface InDocCatalogDAO {
	
	public boolean createInDocCatolog(InDocCatalog exDoc);
	public InDocCatalog getInDocCatologById(long id);
	public List<InDocCatalog> getInDocCatologByDate(LocalDate date);
	public List<InDocCatalog> getInDocCatologByNumber(int number);
	public InDocCatalog getInDocCatologBySnumber(String numberString);
	public boolean deleteInDocCatologDoc(long id);
	public boolean hasError();
}
