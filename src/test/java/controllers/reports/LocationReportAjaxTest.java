package controllers.reports;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pages.ajax.LocationReportAjax;

public class LocationReportAjaxTest {
	MockMvc mockMvc;

	@Test
	public void setBox() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(LocationReportAjax.class).build();
		String searchString = "{\"boxId\":\"3\"}";
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/report/getBoxFilter").content(searchString)
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("boxListId").isArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
