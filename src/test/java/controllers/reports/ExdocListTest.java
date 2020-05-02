package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.ExDocListController;

public class ExdocListTest {
	MockMvc mockMvc;
	@Test
	public void getDocList() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ExDocListController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/exdoclist"));
					
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
