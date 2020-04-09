package savvats;

import javax.validation.constraints.PositiveOrZero;

import models.Box;

public class BoxListLocation extends Box{
	@PositiveOrZero
	private long locationWB;

	public long getLocationWB() {
		return locationWB;
	}

	public void setLocationWB(long locationWB) {
		this.locationWB = locationWB;
	}
	

}
