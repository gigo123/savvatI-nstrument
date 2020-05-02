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
import savvats.DocListWeb;
@Controller
@RequestMapping("/outdoclist")
public class OutDocListController {
	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getOutDocList() {	
		ModelAndView model = new ModelAndView("reports/outDocList");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		DocCatalogDAO 	docCatalogDAO = (DocCatalogDAO) context.getBean("OutDocCatalogDAO");
	List<DocCatalog>  docList = docCatalogDAO.getAllDoc();
	DocListWeb docListw= new DocListWeb(docList);
	System.out.println(docList);

	model.addObject("docList", docListw);
	model.addObject("page", "inDocList");
	return model;
	}
}
