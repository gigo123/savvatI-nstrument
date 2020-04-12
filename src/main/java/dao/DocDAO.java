package dao;

import java.util.List;

import models.DocModel;
import models.ExDoc;
import savvats.DocType;


public interface DocDAO {
	public boolean createExDoc(DocModel Doc);
	public ExDoc getExDocById(long id,DocType docType);
	public List<DocModel> getExDocByCatolog(long id, DocType docType);
	public List<DocModel> getExDocByInstrum(long id,DocType docType);
	public List<DocModel> getExDocByBox(long id,DocType docType);
	public List<DocModel> getExDocByLocation(long id,DocType docType);
	public boolean deleteExDoc(long id,DocType docType);
	public boolean hasError();
	public void closeConection();

}
