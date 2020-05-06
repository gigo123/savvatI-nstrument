package savvats;

import java.util.List;

public class AllReport {
	List<LocationReport> locReportList;
	private float totalAmount;
	public List<LocationReport> getLocReportList() {
		return locReportList;
	}
	public void setLocReportList(List<LocationReport> locReportList) {
		this.locReportList = locReportList;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "AllReport [locReportList=" + locReportList + ", totalAmount=" + totalAmount + "]";
	}
	public AllReport(List<LocationReport> locReportList, float totalAmount) {
		super();
		this.locReportList = locReportList;
		this.totalAmount = totalAmount;
	}
	public AllReport() {
		
	}
}
