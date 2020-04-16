package servlets.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import dao.BoxDAO;
import dao.InstrumentDAO;
import models.Box;
import models.Instrument;
import savvats.AjaxResponseBodyDoc;
import savvats.BoxSearch;
import savvats.Views;

@RestController
@RequestMapping("/createExDoc")
public class ExDocAjax {
	
	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilter")
	public AjaxResponseBodyDoc getSearchResultViaAjax(@RequestBody BoxSearch search) {

		AjaxResponseBodyDoc result = new AjaxResponseBodyDoc();
		result.setCode("200");
        
        long LocId = Long.parseLong(search.getBoxId());
        
        result.setMsg("LocId");
        
        Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		List<Box> BoxList = boxDAO.getAllBoxByLocation(LocId);
		for (Box box : BoxList) {
			boxMap.put(box.getId(), box.getNumber());
		}
		result.setBoxMap(boxMap);
		return result;

	}

}
