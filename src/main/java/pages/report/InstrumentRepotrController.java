package pages.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.LocationDAO;
import savvats.BoxReport;
import savvats.LocationReport;
import savvats.ReportSettings;
import savvats.utils.ControllerReportsWorker;

@RequestMapping("/instrumentReport")
public class InstrumentRepotrController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstReportForm() {
		ModelAndView model = new ModelAndView("reports/InstrumentReport");
		model.addObject("reportSettings", new ReportSettings());
		model.addObject("page", "locReport");

		return model;
	}

	@SuppressWarnings("resource")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generateInstReport(@ModelAttribute("ReportSettings") ReportSettings settings ) {
		ModelAndView model = new ModelAndView("reports/InstrumentReport");
		
		model.addObject("page", "exDocList");
		return model;
	}
}
