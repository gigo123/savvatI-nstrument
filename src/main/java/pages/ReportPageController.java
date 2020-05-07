package pages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/report")
public class ReportPageController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnString(HttpSession session) {
		ModelAndView model = new ModelAndView("ReportView");
		model.addObject("page", "operation");
		return model;
	}
}
