package servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import models.ExDoc;

@Controller
@RequestMapping("/createExDoc")
public class CreateExDoc {
	private boolean error = false;
	private StringBuilder errorText;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getExDocCF() {
		ModelAndView model = new ModelAndView("CreateExDoc", "command", new ExDoc());
		return model;
	}

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postBoxCF(@ModelAttribute("SpringWeb") ExDoc doc, ModelMap model) {
		error = false;
		errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		return getExDocCF();
	}

	public ModelAndView showInfoPage() {
		ModelAndView model = new ModelAndView();
		if (error) {
			error = false;
			model.addObject("errorText", errorText.toString());
		}
		return model;
	}
}
