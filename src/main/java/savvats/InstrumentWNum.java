package savvats;

import models.Instrument;

public class InstrumentWNum  extends Instrument{
	private int totalAmount;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public InstrumentWNum(String name, String measure, int totalAmount) {
		super(name, measure);
		this.totalAmount = totalAmount;
	}
	public InstrumentWNum(String name, String measure) {
		super(name, measure);
	}

}
