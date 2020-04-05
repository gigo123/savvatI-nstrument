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
import org.springframework.web.servlet.ModelAndView;


import dao.LocationDAO;
import models.Location;
import savvats.BoxListLocation;
import savvats.ControllersCheckWrite;

@Controller
@RequestMapping("/addbox")
public class AddBox {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getBoxCF() {
		ModelAndView model = new ModelAndView("AddBox", "command", new BoxListLocation());

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") BoxListLocation box, ModelMap model) {
		String message = ControllersCheckWrite.addBoxWork(box);
		return showInfoPage(message);
	}

	@SuppressWarnings("resource")
	@ModelAttribute("locationWB")
	public Map<Long, String> getLocationWB() {
		Map<Long, String> locationWB = new HashMap<Long, String>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LoctionDAO");
		List<Location> locList = locDAO.getAllLocatinWB();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
		return locationWB;
	}

	public ModelAndView showInfoPage(String message) {
		ModelAndView model = new ModelAndView("OperationInfo");
		model.addObject("errorText", message);
		model.addObject("page", "addinstument");
		return model;
	}

}
