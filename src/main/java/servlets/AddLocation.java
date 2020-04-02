package servlets;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.LocationDAO;
import models.Location;

@Controller
@RequestMapping("/addlocation")
public class AddLocation {
	private boolean error = false;
	private StringBuilder errorText;
	private LocationDAO locDAO;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLocationCF() {
		ModelAndView model = new ModelAndView("AddLocation", "command", new Location());
		if (error) {
			error = false;
			model.addObject("errorText", errorText.toString());
		}
		return  model;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postLocationCF(@ModelAttribute("SpringWeb")Location location, 
		      ModelMap model) {
		error = false;
		errorText = new StringBuilder("<ul>");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		locDAO = (LocationDAO) context.getBean("LocationDAO");
		if (!locDAO.createLocation(location)) {
			error = true;
			errorText.append("<li>ошибка бази данних </li>");
		}
		/*checkErrors(name);
		if (!error) {
			boolean boxes = true;
			if (hasbox == null) {
				boxes = false;
			}
		}
		*/
		return getLocationCF();

	}

	private void checkErrors(String name) {
		if (name.length() < 4) {
			error = true;
			errorText.append("<li> короткое имя </li>");
		}
		Location loc = locDAO.getLocByName(name);
		if (locDAO.hasError()) {
			error = true;
			errorText.append("<li> ошибка бази данних </li>");
		} else {
			if (loc != null) {
				error = true;
				errorText.append("<li> место хранения существует </li>");
			}
		}
	}

}
