package pages.ajax;

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
import models.Box;
import savvats.SearchById;
import savvats.Views;
import savvats.ajax.AjaxResponseDocBox;

@RestController
@RequestMapping("/report")
public class LocationReportAjax {
	@SuppressWarnings("resource")
	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilter")
	public AjaxResponseDocBox getSearchBoxResultViaAjax(@RequestBody SearchById search) {
		AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		String msg = "ок";
		long LocId = search.getBoxId();
		msg = msg + " " + LocId;
		Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
		List<Box> BoxList = boxDAO.getAllBoxByLocation(LocId);
		for (Box box : BoxList) {
			boxMap.put(box.getId(), box.getNumber());
		}
		result.setMsg(msg);
		result.setBoxListId(boxMap);
		return result;

	}
}
