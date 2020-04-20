package controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.create.AddBox;



public class AddBoxControllerTest {
	MockMvc mockMvc;
	
	@Test
	public void setBox() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(AddBox.class).build();
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/addbox")
					.param("locationWB","10")
					.param("manyBox", "O")
					.param("number", "1"));
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
