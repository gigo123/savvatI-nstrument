package savvats;

import models.DocModel;
import models.ExDoc;

public class ExDocTempStore {
	private String errorString;
	private DocModel doc;
	private long outStorageId;
	
	public long getOutStorageId() {
		return outStorageId;
	}
	public void setOutStorageId(long outStorageId) {
		this.outStorageId = outStorageId;
	}
	public String getErrorString() {
		return errorString;
	}
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}
	public DocModel getDoc() {
		return doc;
	}
	public void setDoc(DocModel doc) {
		this.doc = doc;
	}
	
	@Override
	public String toString() {
		return "ExDocTempStore [errorString=" + errorString + ", doc=" + doc + ", outStorageId=" + outStorageId + "]";
	}
	
	public ExDocTempStore(String errorString, DocModel doc, long outStorageId) {
		super();
		this.errorString = errorString;
		this.doc = doc;
		this.outStorageId = outStorageId;
	}
	public ExDocTempStore() {
		
	}

}
