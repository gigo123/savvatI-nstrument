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
import models.Instrument;

@Controller
@RequestMapping("/addinstument")
public class AddInstrument {
	private boolean error = false;
	private StringBuilder errorText;
	private InstrumentDAO instDAO;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstrumentCF() {
		ModelAndView model = new ModelAndView("AddInstrument", "command", new Instrument());
		if (error) {
			error = false;
			model.addObject("errorText", errorText.toString());
		}
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postInstrumentCF(@ModelAttribute("SpringWeb") Instrument instrument, ModelMap model) {
		error = false;
		errorText = new StringBuilder("<ul>");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		checkErrors(instrument);
		if (!error) {
			if (!instDAO.createInstrument(instrument)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		return getInstrumentCF();
	}

	private void checkErrors(Instrument inst) {
		if (inst.getName().length() < 4) {
			error = true;
			errorText.append("<li> короткое имя </li>");
		}
		Instrument instrum = instDAO.getInstrumentByName(inst.getName());
		if (instDAO.hasError()) {
			error = true;
			errorText.append("<li> ошыбка бази данних </li>");
		} else {
			if (instrum != null) {
				error = true;
				errorText.append("<li> место хранения существует </li>");
			}
		}
	}

}
