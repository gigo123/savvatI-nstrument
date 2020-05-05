package savvats;

import java.util.List;

public class LocationReport {
	private String name;
	private float totalAmount;
	private List<BoxReport> reportItems ;
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
	public List<BoxReport> getReportItems() {
		return reportItems;
	}
	public void setReportItems(List<BoxReport> reportItems) {
		this.reportItems = reportItems;
	}
	@Override
	public String toString() {
		return "LocationReport [name=" + name + ", totalAmount=" + totalAmount + ", reportItems=" + reportItems + "]";
	}
	public LocationReport(String name, float totalAmount, List<BoxReport> reportItems) {
		super();
		this.name = name;
		this.totalAmount = totalAmount;
		this.reportItems = reportItems;
	}
	public LocationReport() {
		
	}
}
