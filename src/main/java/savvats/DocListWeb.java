package savvats;

import java.util.List;

import models.DocCatalog;

public class DocListWeb {
	List<DocCatalog>  docList ;

	public List<DocCatalog> getDocList() {
		return docList;
	}

	public void setDocList(List<DocCatalog> docList) {
		this.docList = docList;
	}

	public DocListWeb(List<DocCatalog> docList) {
		super();
		this.docList = docList;
	}

	@Override
	public String toString() {
		return "DocListWeb [docList=" + docList + "]";
	}
	public DocListWeb()
	{
		
	}
	
}
