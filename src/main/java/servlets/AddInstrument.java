package servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.Instrument;
import savvats.ControllersCheckWrite;

@Controller
@RequestMapping("/addinstument")
public class AddInstrument {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstrumentCF() {
		ModelAndView model = new ModelAndView("AddInstrument", "command", new Instrument());
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postInstrumentCF(@ModelAttribute("SpringWeb") Instrument instrument, ModelMap model) {
		String message = ControllersCheckWrite.addInstrumentWork(instrument);
		return showInfoPage(message);
	}

	public ModelAndView showInfoPage(String message) {
		ModelAndView model = new ModelAndView("OperationInfo");
		model.addObject("errorText", message);
		model.addObject("page", "addinstument");
		return model;
	}

}
