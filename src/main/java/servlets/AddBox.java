package servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.InstrumentDAO;
import models.Box;
import models.Instrument;

@Controller
@RequestMapping("/addbox")
public class AddBox {
	private boolean error = false;
	private StringBuilder errorText;
	private InstrumentDAO instDAO;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstrumentCF() {
		ModelAndView model = new ModelAndView("AddBox", "command", new Box());
		if (error) {
			error = false;
			model.addObject("errorText", errorText.toString());
		}
		return model;
	}

	

	

}
