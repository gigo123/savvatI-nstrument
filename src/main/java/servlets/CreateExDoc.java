package servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

@Controller
@RequestMapping("/createExDoc")
public class CreateExDoc {
	private boolean error = false;
	private StringBuilder errorText;
	private Map<Integer, ExDocWEB> docMap= new HashMap<Integer, ExDocWEB>();
	private int docCount=0;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getExDocCF() {
		docMap.put(docCount, new ExDocWEB());
		docCount++;
		//ModelAndView model = new ModelAndView("CreateExDoc");
		//model.addObject("docMap", docMap);
		ExDocWEB doc = new ExDocWEB();
		doc.setId(1);
		ModelAndView model = new ModelAndView("CreateExDoc", "command", doc);
		//model.addObject("docMap", docMap);
		//model.addObject("doc", new ExDocWEB());
		//ExDocMap docMap = new ExDocMap();
		//ModelAndView model = new ModelAndView("CreateExDoc", "command", docMap);
		model = createMaps(model);
		return model;
	}

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") ExDocWEB doc, ModelMap model) {
		String message=doc.toString();
		ModelAndView model1 = new ModelAndView("OperationInfo");
		model1.addObject("errorText", message);
		return model1;
	}


	
	private ModelAndView createMaps(ModelAndView model) {
		
		 Map<Long, String> lociationMap= new HashMap<Long, String>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
			LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
			List<Location> locList = locDAO.getAllLocatin();
			for (Location location : locList) {
				lociationMap.put(location.getId(), location.getName());
			}
			model.addObject("lociationMap", lociationMap);
		/*	Map<Long, String> BoxMap= new HashMap<Long, String>();
				BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
				List<Box> boxList = boxDAO.getAllBoxByLocation(idLocation);
				for (Location location : locList) {
					LociationMap.put(location.getId(), location.getName());
				}
				*/
			 Map<Long, String> instrumentMap= new HashMap<Long, String>();
				InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
				List<Instrument> instList =instDAO.getAllInstrument();
				for (Instrument instrument : instList) {
					instrumentMap.put(instrument.getId(), instrument.getName());
				}
				model.addObject("instrumentMap", instrumentMap);
		return model;
		
		
	}
}
