package servlets;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainPageController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnString(HttpSession session) {
		ModelAndView model = new ModelAndView("MainView");
		model.addObject("page", "main");
		return model;
	}

}
