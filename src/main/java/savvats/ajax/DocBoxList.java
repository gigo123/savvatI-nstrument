package savvats.ajax;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import savvats.Views;

public class DocBoxList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8547032882410410269L;
	
	
	@JsonView(Views.Public.class)
	Map<Long, Integer> BoxMap;

	
	public Map<Long, Integer> getBoxMap() {
		return BoxMap;
	}

	public void setBoxMap(Map<Long, Integer> boxMap) {
		this.BoxMap = boxMap;
	}

	@Override
	public String toString() {
		return "DocBoxList [BoxMap=" + BoxMap + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BoxMap == null) ? 0 : BoxMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocBoxList other = (DocBoxList) obj;
		if (BoxMap == null) {
			if (other.BoxMap != null)
				return false;
		} else if (!BoxMap.equals(other.BoxMap))
			return false;
		return true;
	}
	
}
