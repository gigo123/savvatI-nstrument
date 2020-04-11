package savvats;

import models.ExDoc;

public class ExDocTempStore {
	private String errorString;
	private ExDoc doc;
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
	public ExDoc getDoc() {
		return doc;
	}
	public void setDoc(ExDoc doc) {
		this.doc = doc;
	}
	
	@Override
	public String toString() {
		return "ExDocTempStore [errorString=" + errorString + ", doc=" + doc + ", outStorageId=" + outStorageId + "]";
	}
	
	public ExDocTempStore(String errorString, ExDoc doc, long outStorageId) {
		super();
		this.errorString = errorString;
		this.doc = doc;
		this.outStorageId = outStorageId;
	}
	public ExDocTempStore() {
		
	}

}
