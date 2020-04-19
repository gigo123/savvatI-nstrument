package savvats;



import models.Box;

public class BoxListLocation extends Box{
	private String locationWB;
	private String startNum;
	private String endNum;
	private String manyBox;
	
	public String getManyBox() {
		return manyBox;
	}

	public void setManyBox(String manyBox) {
		this.manyBox = manyBox;
	}

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

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
