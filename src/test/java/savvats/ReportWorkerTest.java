package savvats;

import org.junit.jupiter.api.Test;

public class ReportWorkerTest {
	@Test
	void getBox() {
		ReportsWorker.getInstInBox(9);
	}
	
	@Test
	void getLocation() {
		ReportsWorker.getInstInLocation(7);
	}
	@Test
	void getALL() {
		ReportsWorker.AllReport();
	}
}
