package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.InstrumentRepotrController;

public class InstrumentReportTest {
MockMvc mockMvc;
	
	@Test
	public void instrumentReport() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(InstrumentRepotrController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/instrumentReport"));
			mockMvc.perform(MockMvcRequestBuilders.post("/instrumentReport")
					.param("id","2"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
