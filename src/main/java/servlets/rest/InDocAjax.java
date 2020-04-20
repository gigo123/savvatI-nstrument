package servlets.rest;

import java.util.ArrayList;
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
import savvats.SearchByIdList;
import savvats.Views;
import savvats.ajax.AjaxResponseDocBox;
import savvats.ajax.AjaxResponseDocInstrument;
import savvats.ajax.DocBoxList;

@RestController
@RequestMapping("/createInDoc")
public class InDocAjax {

	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilter")
	public AjaxResponseDocBox getSearchBoxResultViaAjax(@RequestBody SearchByIdList search) {
AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		// long LocId = Long.parseLong(search.getBoxId());
		List<Integer> boxIdList = search.getBoxId();

		String msg="";
		List<DocBoxList> boxListId = new ArrayList<DocBoxList>();
		
		for (int boxId : boxIdList) {
			long LocId = boxId;
			//msg=msg + " " + LocId;
			Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
			List<Box> BoxList = boxDAO.getAllBoxByLocation(LocId);
			for (Box box : BoxList) {
				boxMap.put(box.getId(), box.getNumber());
			}
			DocBoxList boxListMap = new DocBoxList();
			boxListMap.setBoxMap(boxMap);
			boxListId.add(boxListMap);
			System.out.println(boxListId);
			msg= boxListId.toString();
		}
		result.setMsg(msg);
		result.setBoxListId(boxListId);
		return result;

	}

}
