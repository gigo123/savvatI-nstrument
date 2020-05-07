package pages.create;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import dao.InstrumentDAO;
import dao.LocationDAO;
import models.Instrument;
import models.Location;
import savvats.DocType;
import savvats.ExDocWEB;
import savvats.ExDocWEBList;
import savvats.utils.ControllersCheckWDoc;

@Controller
@RequestMapping("/createExDoc")
public class CreateExDoc {
	ExDocWEBList exDocWEBList = null;

	@ModelAttribute("exDocWEBList")
	public ExDocWEBList createExDocWEBListModel() {
		return new ExDocWEBList();
	}


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getExDocCF() {
		ExDocWEB doc = new ExDocWEB();
		exDocWEBList = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(doc);
		exDocWEBList.setDocList(docList);
		ModelAndView model = new ModelAndView("CreateExDoc");
		model.addObject("exDocWEBList", exDocWEBList);
		model.addObject("page", "exdoc");
		return model;
	}

	@SuppressWarnings("resource")
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

	@SuppressWarnings("resource")
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

	@RequestMapping(method = RequestMethod.GET, params = { "addRow" })
	public ModelAndView docAddRow() {
		if (exDocWEBList != null) {
			exDocWEBList.getDocList().add(new ExDocWEB());
		}
		ModelAndView model = new ModelAndView("CreateExDoc");
		model.addObject("exDocWEBList", exDocWEBList);
		model.addObject("page", "exdoc");
		return model;

	}

	@RequestMapping(method = RequestMethod.GET, params = { "removeRow" })
	public ModelAndView docRemoveRow(@RequestParam("removeRow") String id) {
		System.out.println(id);
		int idInt = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			ModelAndView model = new ModelAndView("CreateExDoc");
			model.addObject("exDocWEBList", exDocWEBList);
			return model;
		}
		if (exDocWEBList != null) {
			if (exDocWEBList.getDocList().size() == 1) {
				ExDocWEB doc = new ExDocWEB();
				List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
				docList.add(doc);
				exDocWEBList.setDocList(docList);
			}
			if (exDocWEBList.getDocList().size() > 1) {
				if (idInt <= exDocWEBList.getDocList().size()) {
					exDocWEBList.getDocList().remove(idInt);
				}
			}
		}
		ModelAndView model = new ModelAndView("CreateExDoc");
		model.addObject("exDocWEBList", exDocWEBList);
		model.addObject("page", "exdoc");
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

}
