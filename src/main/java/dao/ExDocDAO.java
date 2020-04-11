package dao;

import java.util.List;


import models.ExDoc;


public interface ExDocDAO {
	public boolean createExDoc(ExDoc exDoc);
	public ExDoc getExDocById(long id);
	public List<ExDoc> getExDocByCatolog(long id);
	public List<ExDoc> getExDocByInstrum(long id);
	public List<ExDoc> getExDocByBox(long id);
	public List<ExDoc> getExDocByLocation(long id);
	public boolean deleteExDoc(long id);
	public boolean hasError();
	public void closeConection();

}
