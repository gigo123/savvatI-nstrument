package pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.Instrument;
import savvats.ControllersCheckWrite;

@Controller
@RequestMapping("/addinstument")
public class AddInstrument {
	@ModelAttribute("instrument")
	public Instrument createInstrumentModel() {
		return new Instrument();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstrumentCF() {
		ModelAndView model = new ModelAndView("AddInstrument", "command", new Instrument());
		model.addObject("page", "instrument");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postInstrumentCF(@ModelAttribute("instrument") @Validated Instrument instrument, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "AddInstrument";
		}
		model.addAttribute("errorText", ControllersCheckWrite.addInstrumentWork(instrument));
		return "OperationInfo";
	}


}
