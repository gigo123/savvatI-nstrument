package controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import savvats.SearchById;
import servlets.rest.ExDocAjax;

@WebAppConfiguration
public class ExDocAjaxTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Test
	public void setBox() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ExDocAjax.class).build();
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		List<Integer> boxIdList = new ArrayList<Integer>();
		boxIdList.add(1);
		boxIdList.add(2);
		SearchById search = new SearchById();
		search.setBoxId(boxIdList);
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
	
	
	@Test
	public void setInstrument() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ExDocAjax.class).build();
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		List<Integer> boxIdList = new ArrayList<Integer>();
		boxIdList.add(1);
		boxIdList.add(2);
		SearchById search = new SearchById();
		search.setBoxId(boxIdList);
		// создаем POST-запрос, набиваем его параметрами и выполняем
		String searchString = "{\"boxId\":[\"13\",\"9\"]}";
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
