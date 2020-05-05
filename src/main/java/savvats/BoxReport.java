package savvats;

import java.util.List;

public class BoxReport {
	private String name;
	private float totalAmount;
	private List<ReportItem> reportItems ;
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
	public List<ReportItem> getReportItems() {
		return reportItems;
	}
	public void setReportItems(List<ReportItem> reportItems) {
		this.reportItems = reportItems;
	}
	@Override
	public String toString() {
		return "BoxReport [name=" + name + ", totalAmount=" + totalAmount + ", reportItems=" + reportItems + "]";
	}
	public BoxReport(String name, float totalAmount, List<ReportItem> reportItems) {
		super();
		this.name = name;
		this.totalAmount = totalAmount;
		this.reportItems = reportItems;
	}
	
	public BoxReport() {}
	
	
	
}
