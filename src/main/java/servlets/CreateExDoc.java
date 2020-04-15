package servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.InstrumentDAO;
import dao.LocationDAO;
import models.Instrument;
import models.Location;
import savvats.ControllersCheckWDoc;
import savvats.DocType;
import savvats.ExDocWEB;
import savvats.ExDocWEBList;

@Controller
@RequestMapping("/createExDoc")
public class CreateExDoc {
	ExDocWEBList exDocWEBList = null;
	
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getExDocCF() {
			ExDocWEB doc = new ExDocWEB();
			exDocWEBList = new ExDocWEBList();
			List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
			docList.add(doc);
			exDocWEBList.setDocList(docList);
		ModelAndView model = new ModelAndView("CreateExDoc");
		model.addObject("exDocWEBList", exDocWEBList);
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postBoxCF(@ModelAttribute("exDocWEBList") @Validated ExDocWEBList exDocWEBList,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "CreateExDoc";
		}
		String message;
		message = ControllersCheckWDoc.createExDocUnwrap(exDocWEBList, DocType.EXDOC);
		if (message.equals("документ создан")) {
			exDocWEBList.getDocList().add(new ExDocWEB());
		}
		model.addAttribute("errorText", message);
		return "OperationInfo";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "addRow"})
	public ModelAndView getProductListCategory() {
		if (exDocWEBList != null) {
		exDocWEBList.getDocList().add(new ExDocWEB());
		}
	
		return getExDocCF();
	}

	@ModelAttribute("exDocWEBList")
	public ExDocWEBList createExDocWEBListModel() {
		return new ExDocWEBList();
	}

	@ModelAttribute("locationList")
	private Map<Long, String> getLocationList() {
		Map<Long, String> lociationMap = new HashMap<Long, String>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		List<Location> locList = locDAO.getAllLocatin();
		for (Location location : locList) {
			lociationMap.put(location.getId(), location.getName());
		}
		return lociationMap;
	}

	@ModelAttribute("instrumentMap")
	private Map<Long, String> getInstrumentMap() {
		Map<Long, String> instrumentMap = new HashMap<Long, String>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		List<Instrument> instList = instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}

	@ModelAttribute("boxMap")
	private Map<Long, String> getBoxMap() {
		Map<Long, String> instrumentMap = new HashMap<Long, String>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		List<Instrument> instList = instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}

}
