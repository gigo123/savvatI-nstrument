package savvats;

public class ReportItem {
	private String name;
	private float amount;
	private String measure;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public ReportItem(String name, float amount, String measure) {
		super();
		this.name = name;
		this.amount = amount;
		this.measure = measure;
	}
	@Override
	public String toString() {
		return "ReportItem [name=" + name + ", amount=" + amount + ", measure=" + measure + "]";
	}
	public ReportItem() {}
	
	

}
