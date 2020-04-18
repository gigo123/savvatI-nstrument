package savvats.ajax;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class DocInstrumentMap {
	@JsonView(Views.Public.class)
	Map<Long, String> instrumentMap;

	public Map<Long, String> getInstrumentMap() {
		return instrumentMap;
	}

	public void setInstrumentMap(Map<Long, String> instrumentMap) {
		this.instrumentMap = instrumentMap;
	}

	@Override
	public String toString() {
		return "DocInstrumentMap [instrumentMap=" + instrumentMap + "]";
	}
	
	
	
}
