package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.LocationReportController;

public class LocationReportTest {
MockMvc mockMvc;
	
	@Test
	public void locationReport() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(LocationReportController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/locationReport"));
			mockMvc.perform(MockMvcRequestBuilders.post("/locationReport")
					.param("locationId","10"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
