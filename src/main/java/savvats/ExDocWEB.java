package savvats;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonView;

public class ExDocWEB {
	private long id;
	@NotEmpty
	@JsonView(Views.Public.class)
	private String outLocation;
	// @NotEmpty
	@JsonView(Views.Public.class)
	private String inLocation;
	@PositiveOrZero(message = "ячейка должна быть положительним числом")
	@JsonView(Views.Public.class)
	private int outBox;
	// @PositiveOrZero(message = "ячейка должна быть положительним числом")
	@JsonView(Views.Public.class)
	private int inBox;
	@NotEmpty
	@JsonView(Views.Public.class)
	private String instrument;
	@Positive(message = "количество должно быть положительним числом")
	private float amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutLocation() {
		return outLocation;
	}

	public void setOutLocation(String outLocation) {
		this.outLocation = outLocation;
	}

	public String getInLocation() {
		return inLocation;
	}

	public void setInLocation(String inLocation) {
		this.inLocation = inLocation;
	}

	public int getOutBox() {
		return outBox;
	}

	public void setOutBox(int outBox) {
		this.outBox = outBox;
	}

	public int getInBox() {
		return inBox;
	}

	public void setInBox(int inBox) {
		this.inBox = inBox;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public ExDocWEB(String outLocation, String inLocation, int outBox, int inBox, String instrument, float amount) {
		super();
		this.outLocation = outLocation;
		this.inLocation = inLocation;
		this.outBox = outBox;
		this.inBox = inBox;
		this.instrument = instrument;
		this.amount = amount;
	}

	public ExDocWEB() {

	}

	@Override
	public String toString() {
		return "ExDocWEB [id=" + id + ", outLocation=" + outLocation + ", inLocation=" + inLocation + ", outBox="
				+ outBox + ", inBox=" + inBox + ", instrument=" + instrument + ", amount=" + amount + "]";
	}

}
