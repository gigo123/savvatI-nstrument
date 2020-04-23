package pages;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.DocCatalogDAO;


@Controller
@RequestMapping("/exdoclist")
public class ExDocListController {



	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getexDocList() {
		ModelAndView model = new ModelAndView("ExDocList");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		DocCatalogDAO 	docCatalogDAO = (DocCatalogDAO) context.getBean("ExDocCatalogDAO");
	//	docCatalogDAO.get
	//	model.addObject("page", "exdoclist");
		return model;
	}

}
