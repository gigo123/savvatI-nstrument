package pages.report;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.DocCatalogDAO;
import models.DocCatalog;
import savvats.BoxListLocation;


@Controller
@RequestMapping("/exdoclist")
public class ExDocListController {



	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getexDocList() {	
		ModelAndView model = new ModelAndView("ExDocList");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		DocCatalogDAO 	docCatalogDAO = (DocCatalogDAO) context.getBean("ExDocCatalogDAO");
	List<DocCatalog>  docList = docCatalogDAO.getAllDoc();
	//docList.get(1).ge

	model.addObject("DocList", docList);
	model.addObject("page", "exDocList");
	return model;
	//	model.addObject("page", "exdoclist");
	}

}
