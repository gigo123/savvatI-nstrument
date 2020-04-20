package controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.create.CreateOutDoc;

public class CreateOutDocControllerTest {
	MockMvc mockMvc;
	@Test
	public void setOutDoc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(CreateOutDoc.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/createOutDoc")
					.param("docList[0].outLocation","1")
					.param("docList[0].outBox", "9")
					.param("docList[0].instrument", "2")
					.param("docList[0].amount","0.3"));
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
