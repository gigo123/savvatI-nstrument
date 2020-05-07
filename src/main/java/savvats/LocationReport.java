package savvats;

import java.util.List;

public class LocationReport {
	private String name;
	private float totalAmount;
	private List<BoxReport> reportBox ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "LocationReport [name=" + name + ", totalAmount=" + totalAmount + ", reportItems=" + reportBox + "]";
	}
	public LocationReport(String name, float totalAmount, List<BoxReport> reportBox) {
		super();
		this.name = name;
		this.totalAmount = totalAmount;
		this.reportBox = reportBox;
	}
	public List<BoxReport> getReportBox() {
		return reportBox;
	}
	public void setReportBox(List<BoxReport> reportBox) {
		this.reportBox = reportBox;
	}
	public LocationReport() {
		
	}
}
