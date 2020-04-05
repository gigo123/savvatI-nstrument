package servlets;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.Location;
import savvats.ControllersCheckWrite;

@Controller
@RequestMapping("/addlocation")
public class AddLocation {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLocationCF() {
		ModelAndView model = new ModelAndView("AddLocation", "command", new Location());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postLocationCF(@ModelAttribute("SpringWeb") Location location, ModelMap model) {
		String message = ControllersCheckWrite.addLocationWork(location);
		return showInfoPage(message);
	}

	public ModelAndView showInfoPage(String message) {
		ModelAndView model = new ModelAndView("OperationInfo");
		model.addObject("errorText", message);
		return model;
	}

}
