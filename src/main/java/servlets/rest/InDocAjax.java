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
import dao.StorageDAO;
import models.Box;
import models.Storage;
import savvats.SearchById;
import savvats.Views;
import savvats.ajax.AjaxResponseDocBox;
import savvats.ajax.AjaxResponseDocInstrument;

@RestController
@RequestMapping("/createInDoc")
public class InDocAjax {

	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilter")
	public AjaxResponseDocBox getSearchBoxResultViaAjax(@RequestBody SearchById search) {

		AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");

		long LocId = Long.parseLong(search.getBoxId());

		result.setMsg("LocId");

		Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		List<Box> BoxList = boxDAO.getAllBoxByLocation(LocId);
		for (Box box : BoxList) {
			boxMap.put(box.getId(), box.getNumber());
		}
		result.setBoxMap(boxMap);
		return result;

	}

	@JsonView(Views.Public.class)
	@RequestMapping("/getInstrumentFilter")
	public AjaxResponseDocInstrument getSearchInstrumentResultViaAjax(@RequestBody SearchById search) {

		AjaxResponseDocInstrument result = new AjaxResponseDocInstrument();
		result.setCode("200");

		long boxId = Long.parseLong(search.getBoxId());

		result.setMsg("boxId");

		Map<Long, String> instrumentMap = new HashMap<Long, String>();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		List<Storage> storeList = storeDAO.getStorageByBox(boxId);

		for (Storage storsge : storeList) {
			instrumentMap.put(storsge.getInstrument().getId(), storsge.getInstrument().getName());
		}
		result.setInstrumentMap(instrumentMap);
		return result;

	}

}
