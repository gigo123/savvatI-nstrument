package servlets;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String postLocationCF(@ModelAttribute("location") @Validated Location location, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "AddLocation";
		}
		model.addAttribute("errorText",ControllersCheckWrite.addLocationWork(location));
		return "OperationInfo";
	}
	@ModelAttribute("location")
	public Location createLocationModel() {
		return new Location();
	}

}
