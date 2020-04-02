package servlets;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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
		ModelAndView model = new ModelAndView("AddLocationView");

		return model;

	}

	@RequestMapping(method = RequestMethod.POST, params = { "name", "hasbox" })
	public ModelAndView postLocationCF(@RequestParam("name") String name, @RequestParam("hasbox") String hasbox) {
		error = false;
		errorText = new StringBuilder("<ul>");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		locDAO = (LocationDAO) context.getBean("LocationDAO");
		checkErrors(name);
		if (!error) {
			boolean boxes = true;
			if (hasbox == null) {
				boxes = false;
			}
			Location location = new Location(name, boxes);
			if (!locDAO.createLocation(location)) {
				error = true;
				errorText.append("<li>ошибка бази данних </li>");
			}
		}
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
