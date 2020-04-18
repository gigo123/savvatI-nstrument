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
import dao.StorageDAO;
import models.Box;
import models.Storage;
import savvats.SearchById;
import savvats.Views;
import savvats.ajax.AjaxResponseDocBox;
import savvats.ajax.AjaxResponseDocInstrument;
import savvats.ajax.DocBoxList;
import savvats.ajax.DocInstrumentMap;

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


	  @JsonView(Views.Public.class)
	  @RequestMapping("/getInstrumentFilter")
	  public AjaxResponseDocInstrument getSearchInstrumentViaAjax(@RequestBody SearchById search) { 
		  AjaxResponseDocInstrument result = new AjaxResponseDocInstrument();
		result.setCode("200");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		
		// long LocId = Long.parseLong(search.getBoxId());
		List<Integer> boxIdList = search.getBoxId();

		String msg="";
		List<DocInstrumentMap> boxListId = new ArrayList<DocInstrumentMap>();
		
		for (int boxId : boxIdList) {
			long LocId = boxId;
			//msg=msg + " " + LocId;
			Map<Long, String> instrumentMap = new HashMap<Long, String>();
			List<Storage> storeList = storeDAO.getStorageByBox(boxId);
			for (Storage storsge : storeList) {
				instrumentMap.put(storsge.getInstrument().getId(), storsge.getInstrument().getName());
			}
			DocInstrumentMap instrumentListMap = new DocInstrumentMap();
			instrumentListMap.setInstrumentMap(instrumentMap);
			boxListId.add(instrumentListMap);
			System.out.println(boxListId);
			msg= boxListId.toString();
		}
		result.setMsg(msg);
		result.setBoxListId(boxListId);
		return result;
	  
	  }
	
}
