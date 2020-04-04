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
import dao.InstrumentDAO;
import models.Box;
import models.Instrument;
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

	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") Box box, ModelMap model) {
		error = false;
		errorText = new StringBuilder("<ul>");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		boxDAO = (BoxDAO) context.getBean("BoxDAO");
		if (!error) {
			if (!boxDAO.createBox(box)){
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		return getBoxCF();
	}
	

	   @ModelAttribute("locationWB")
	   public Map<Long, String> getLocationWB() {
	      Map<Long, String> locationWB = new HashMap<Long, String>();
	      locationWB.put((long) 1, "Hibernate");
	      locationWB.put((long) 2, "Spring");
	      locationWB.put((long) 3, "Apache Wicket");
	      locationWB.put((long) 4, "Struts");
	      return locationWB;
	   }

}
