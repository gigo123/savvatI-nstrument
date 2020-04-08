package servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.BoxDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import models.Box;
import models.Instrument;
import models.Location;
import savvats.BoxListLocation;
import savvats.ExDocWEB;
import savvats.ExDocWEBList;

@Controller
@RequestMapping("/createExDoc")
public class CreateExDoc {
	private boolean error = false;
	private StringBuilder errorText;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getExDocCF() {
	
	
		ExDocWEB doc = new ExDocWEB();
		ExDocWEBList exDocWEBList = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(doc);
		exDocWEBList.setDocList(docList);
		ModelAndView model = new ModelAndView("CreateExDoc");
		model.addObject("exDocWEBList", exDocWEBList);
	//	model = createMaps(model);
		return model;
	}

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") @Validated ExDocWEBList exDocWEBList, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			System.out.println("error");
			ModelAndView model1 = new ModelAndView("CreateExDoc");
	         return model1;
	      }

		String message=exDocWEBList.toString();
		
		ModelAndView model1 = new ModelAndView("OperationInfo");
		model1.addObject("errorText", message);
		return model1;
	}

	@ModelAttribute("locationList")
	private  Map<Long, String> getLocationList(){
		 Map<Long, String> lociationMap= new HashMap<Long, String>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
			LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
			List<Location> locList = locDAO.getAllLocatin();
			for (Location location : locList) {
				lociationMap.put(location.getId(), location.getName());
			}
			return lociationMap;
	}
	@ModelAttribute("instrumentMap")
	private Map<Long, String> getInstrumentMap(){
		Map<Long, String> instrumentMap= new HashMap<Long, String>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		List<Instrument> instList =instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}
	@ModelAttribute("boxMap")
	private Map<Long, String> getBoxMap(){
		Map<Long, String> instrumentMap= new HashMap<Long, String>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		List<Instrument> instList =instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}
	
}
