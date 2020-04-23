package pages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/operation")
public class OperationPageController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnString(HttpSession session) {
		ModelAndView model = new ModelAndView("OperationView");
		model.addObject("page", "operation");
		return model;
	}
}
