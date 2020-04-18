package savvats.ajax;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class AjaxResponseDocBox  extends AjaxResponse{
	
    @JsonView(Views.Public.class)
    List<DocBoxList> boxListId;

	public List<DocBoxList> getBoxListId() {
		return boxListId;
	}

	public void setBoxListId(List<DocBoxList> boxListId) {
		this.boxListId = boxListId;
	}

    
    

}
