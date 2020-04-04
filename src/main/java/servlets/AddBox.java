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

import dao.BoxDAO;
import dao.LocationDAO;
import models.Box;
import models.Instrument;
import models.Location;
import savvats.BoxListLocation;

@Controller
@RequestMapping("/addbox")
public class AddBox {
	private boolean error = false;
	private StringBuilder errorText;
	private BoxDAO boxDAO;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getBoxCF() {
		ModelAndView model = new ModelAndView("AddBox", "command", new BoxListLocation());
		if (error) {
			error = false;
			model.addObject("errorText", errorText.toString());
		}
		return model;
	}

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") BoxListLocation box, ModelMap model) {
		error = false;
		errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		boxDAO = (BoxDAO) context.getBean("BoxDAO");
		checkErrors(box);
		if (!error) {
			if (!boxDAO.createBox(box)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		return getBoxCF();
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

	private void checkErrors(BoxListLocation box) {
		if (box.getNumber() == 0) {
			error = true;
			errorText.append("<li> не можеть бить  нуловой номер </li>");
		}

		if (box.getLocation() != null) {
			error = true;
			errorText.append("<li> не вибрано место хранения </li>");
		}
	}

}
