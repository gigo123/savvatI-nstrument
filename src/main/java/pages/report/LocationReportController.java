package pages.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.DocCatalogDAO;
import dao.LocationDAO;
import models.DocCatalog;
import models.Location;
import savvats.DocListWeb;
import savvats.LocationReport;

@Controller
@RequestMapping("/locationReport")
public class LocationReportController {
	private LocationReport locReport;

		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView getLocReportForm() {	
			ModelAndView model = new ModelAndView("reports/LocationReport");
		return model;
		}
		
		@SuppressWarnings("resource")
		@RequestMapping(method = RequestMethod.POST)
		public ModelAndView generateReport() {	
			ModelAndView model = new ModelAndView("reports/LocationReport");
			ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
			DocCatalogDAO 	docCatalogDAO = (DocCatalogDAO) context.getBean("ExDocCatalogDAO");
		List<DocCatalog>  docList = docCatalogDAO.getAllDoc();
		DocListWeb docListw= new DocListWeb(docList);
		System.out.println(docList);
		model.addObject("docList", docListw);
		model.addObject("page", "exDocList");
		return model;
		}
		

		@SuppressWarnings("resource")
		@ModelAttribute("locationList")
		private Map<Long, String> getLocationList() {
			Map<Long, String> lociationMap = new HashMap<Long, String>();
			ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
			LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
			List<Location> locList = locDAO.getAllLocatin();
			for (Location location : locList) {
				lociationMap.put(location.getId(), location.getName());
			}
			return lociationMap;
		}


}
