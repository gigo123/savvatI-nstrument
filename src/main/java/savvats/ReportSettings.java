package savvats;

public class ReportSettings {
	private long locationId;
	private long boxId;
	private boolean box;
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public long getBoxId() {
		return boxId;
	}
	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}
	public boolean isBox() {
		return box;
	}
	public void setBox(boolean box) {
		this.box = box;
	}
	public ReportSettings(long locationId, long boxId, boolean box) {
		super();
		this.locationId = locationId;
		this.boxId = boxId;
		this.box = box;
	}
	public ReportSettings() {
		
	}
	@Override
	public String toString() {
		return "ReportSettings [locationId=" + locationId + ", boxId=" + boxId + ", box=" + box + "]";
	}

}
