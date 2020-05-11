package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.AllLocationReportController;

public class AllReportTest {
MockMvc mockMvc;
	
	@Test
	public void allReport() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(AllLocationReportController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/AlllocationReport"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
