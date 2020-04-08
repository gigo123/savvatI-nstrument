package savvats;

import java.util.List;

import javax.validation.Valid;

public class ExDocWEBList {
	@Valid
	private List<ExDocWEB> docList;
	public List<ExDocWEB> getDocList() {
		return docList;
	}
	public void setDocList(List<ExDocWEB> docList) {
		this.docList = docList;
	}
	
	public ExDocWEBList() {
	}
	@Override
	public String toString() {
		return "ExDocWEBList [docList=" + docList + "]";
	}
	
}
