package controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.create.CreateExDoc;

public class CreateEXDocTest {
	MockMvc mockMvc;
	@Test
	public void setOutDoc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(CreateExDoc.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/createExDoc?removeRow=2"));
					
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
