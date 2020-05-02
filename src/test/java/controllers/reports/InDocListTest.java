package controllers.reports;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.report.InDocListController;

public class InDocListTest {
	MockMvc mockMvc;
	@Test
	public void getDocList() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(InDocListController.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/indoclist"));
		
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
