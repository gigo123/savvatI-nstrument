package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.OutDocListController;

public class OutDocListTest {
	MockMvc mockMvc;
	@Test
	public void getDocList() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(OutDocListController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/outdoclist"));
					
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
