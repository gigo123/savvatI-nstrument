package controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pages.ajax.ExDocAjax;


@WebAppConfiguration
public class ExDocAjaxTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@SuppressWarnings("deprecation")
	@Test
	public void setBox() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ExDocAjax.class).build();
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		
		// создаем POST-запрос, набиваем его параметрами и выполняем
		String searchString = "{\"boxId\":[\"1\",\"7\"]}";
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/createExDoc/getBoxFilter").content(searchString)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("boxListId").isArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void setInstrument() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ExDocAjax.class).build();
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		;
		// создаем POST-запрос, набиваем его параметрами и выполняем
		String searchString = "{\"boxId\":\"35\"}";
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/createExDoc//getInstrumentFilter").content(searchString)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("boxListId").isArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
