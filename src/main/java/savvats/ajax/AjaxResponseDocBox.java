package savvats.ajax;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class AjaxResponseDocBox  extends AjaxResponse{
	

    @JsonView(Views.Public.class)
    Map<Long, Integer> BoxMap;


	public Map<Long, Integer> getBoxMap() {
		return BoxMap;
	}

	public void setBoxMap(Map<Long, Integer> boxMap) {
		BoxMap = boxMap;
	}

    

}
