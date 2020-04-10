package savvats;

import models.ExDoc;

public class ExDocTempStore {
	private String errorString;
	private ExDoc doc;
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
		return "ExDocTempStore [errorString=" + errorString + ", doc=" + doc + "]";
	}
	public ExDocTempStore(String errorString, ExDoc doc) {
		super();
		this.errorString = errorString;
		this.doc = doc;
	}
	public ExDocTempStore() {
		
	}

}
