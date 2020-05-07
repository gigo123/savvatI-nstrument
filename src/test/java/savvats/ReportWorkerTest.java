package savvats;

import org.junit.jupiter.api.Test;

import savvats.utils.ControllerReportsWorker;

public class ReportWorkerTest {
	@Test
	void getBox() {
		ControllerReportsWorker.getInstInBox(9);
	}
	
	@Test
	void getLocation() {
		ControllerReportsWorker.getInstInLocation(7);
	}
	@Test
	void getALL() {
		ControllerReportsWorker.AllReport();
	}
}
