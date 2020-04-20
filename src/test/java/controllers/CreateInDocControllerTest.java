package controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import servlets.AddBox;
import servlets.CreateInDoc;

public class CreateInDocControllerTest {
	MockMvc mockMvc;
	@Test
	public void setBox() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(CreateInDoc.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/createInDoc")
					.param("docList[0].outLocation","1")
					.param("docList[0].outBox", "9")
					.param("docList[0].instrument", "2")
					.param("docList[0].amount","0.2"));
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
