package savvats.ajax;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class AjaxResponseDocInstrument  extends AjaxResponse{
	 @JsonView(Views.Public.class)
	 Map<Long, String> InstrumentMapId;

	public Map<Long, String> getInstrumentMapId() {
		return InstrumentMapId;
	}

	public void setInstrumentMapId(Map<Long, String> instrumentMapId) {
		InstrumentMapId = instrumentMapId;
	}

		

}
