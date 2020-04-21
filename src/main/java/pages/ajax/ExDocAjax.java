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
import dao.StorageDAO;
import models.Box;
import models.Storage;
import savvats.SearchById;
import savvats.Views;
import savvats.ajax.AjaxResponseDocBox;
import savvats.ajax.AjaxResponseDocInstrument;


@RestController
@RequestMapping("/createExDoc")
public class ExDocAjax {

	@SuppressWarnings("resource")
	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilter")
	public AjaxResponseDocBox getSearchBoxResultViaAjax(@RequestBody SearchById search) {

		AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		// long LocId = Long.parseLong(search.getBoxId());
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
	@SuppressWarnings("resource")
	@JsonView(Views.Public.class)
	@RequestMapping("/getBoxFilterNE")
	public AjaxResponseDocBox getSearchBoxNEResultViaAjax(@RequestBody SearchById search) {

		AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		String msg = "ок";
		long LocId = search.getBoxId();
		msg = msg + " " + LocId;
		Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
		List<Box> BoxList = boxDAO.getNotEmptyBoxByLocation(LocId);
		for (Box box : BoxList) {
			boxMap.put(box.getId(), box.getNumber());
		}
		result.setMsg(msg);
		result.setBoxListId(boxMap);
		return result;

	}

	@SuppressWarnings("resource")
	@JsonView(Views.Public.class)
	@RequestMapping("/getInstrumentFilter")
	public AjaxResponseDocInstrument getSearchInstrumentViaAjax(@RequestBody SearchById search) {
		AjaxResponseDocInstrument result = new AjaxResponseDocInstrument();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		String msg = "";
		long boxId = search.getBoxId();
		Map<Long, String> instrumentMap = new HashMap<Long, String>();
		List<Storage> storeList = storeDAO.getStorageByBox(boxId);
		for (Storage storsge : storeList) {
			instrumentMap.put(storsge.getInstrument().getId(), storsge.getInstrument().getName());
		}
		msg = instrumentMap.toString();
		result.setMsg(msg);
		result.setInstrumentMapId(instrumentMap);
		return result;

	}

}
