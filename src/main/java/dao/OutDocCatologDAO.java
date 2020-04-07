package dao;

import java.time.LocalDate;
import java.util.List;

import models.OutDocCatalog;

public interface OutDocCatologDAO {

	public boolean createInDocCatolog(OutDocCatalog exDoc);
	public OutDocCatalog getInDocCatologById(long id);
	public List<OutDocCatalog> getInDocCatologByDate(LocalDate date);
	public List<OutDocCatalog> getInDocCatologByNumber(int number);
	public OutDocCatalog getInDocCatologBySnumber(String numberString);
	public boolean deleteInDocCatologDoc(long id);
	public boolean hasError();
}
