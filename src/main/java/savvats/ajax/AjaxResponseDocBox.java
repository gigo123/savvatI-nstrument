package savvats.ajax;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class AjaxResponseDocBox  extends AjaxResponse{
	
    @JsonView(Views.Public.class)
    Map<Long, Integer>  boxListId;

	public Map<Long, Integer> getBoxListId() {
		return boxListId;
	}

	public void setBoxListId(Map<Long, Integer> boxListId) {
		this.boxListId = boxListId;
	}

    
    

}
