package savvats;

public class ReportItemWLoc  extends ReportItem{
	private String locationName;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public ReportItemWLoc(String name, float amount, String measure, String locationName) {
		super(name, amount, measure);
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return "ReportItemWLoc [locationName=" + locationName + ", getName()=" + getName() + ", getAmount()="
				+ getAmount() + ", getMeasure()=" + getMeasure() + "]";
	}
	

}
