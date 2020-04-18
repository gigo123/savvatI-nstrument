package controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import savvats.SearchById;
import servlets.rest.ExDocAjax;

@WebAppConfiguration
public class ExDocAjaxTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Test
	public void add() {
		this.mockMvc =MockMvcBuilders.standaloneSetup(ExDocAjax.class).build();
	//	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
		List<Integer> boxIdList = new ArrayList<Integer>();
		boxIdList.add(1);
		boxIdList.add(2);
		SearchById search = new SearchById();
		search.setBoxId(boxIdList);
		//  создаем POST-запрос, набиваем его параметрами и выполняем
		String searchString = "{\"boxId\":[\"1\",\"7\"]}";
		System.out.println(searchString);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/createExDoc/getBoxFilter")
			.content(searchString)
			.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
			.andExpect(content().json(searchString) );
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}
	
}
