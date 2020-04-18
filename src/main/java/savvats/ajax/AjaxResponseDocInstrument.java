package savvats.ajax;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class AjaxResponseDocInstrument  extends AjaxResponse{
	 @JsonView(Views.Public.class)
	 List<DocInstrumentMap> InstrumentListId;

		public List<DocInstrumentMap> getBoxListId() {
			return InstrumentListId;
		}

		public void setBoxListId(List<DocInstrumentMap> boxListId) {
			this.InstrumentListId = boxListId;
		}

}
