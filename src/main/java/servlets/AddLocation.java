package servlets;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/addlocation")
public class AddLocation {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLocationCF(HttpSession session) {
		ModelAndView model = new ModelAndView("AddLocationView");
		
		return model;
		
	}
	@RequestMapping(method = RequestMethod.POST, params = { "name", "hasbox"})
	public ModelAndView postLocationCF(@RequestParam("name") String name ,
			@RequestParam("hasbox") String hasbox) {
		return null;
		
	}
}
