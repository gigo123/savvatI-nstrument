package savvats;

import javax.validation.constraints.PositiveOrZero;

import models.Box;

public class BoxListLocation extends Box{
	private String locationWB;

	public String getLocationWB() {
		return locationWB;
	}

	public void setLocationWB(String locationWB) {
		this.locationWB = locationWB;
	}

	@Override
	public String toString() {
		return "BoxListLocation [locationWB=" + locationWB + "]";
	}
	

}
