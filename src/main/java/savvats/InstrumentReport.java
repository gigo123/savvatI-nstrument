package savvats;

import java.util.List;

public class InstrumentReport {
	private long id;
	List<ReportItemWLoc> locReport;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<ReportItemWLoc> getLocReport() {
		return locReport;
	}
	public void setLocReport(List<ReportItemWLoc> locReport) {
		this.locReport = locReport;
	}
	public InstrumentReport() {
		
	}
	@Override
	public String toString() {
		return "InstrumentReport [id=" + id + ", locReport=" + locReport + "]";
	}
	

}
