package pages.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import savvats.AllReport;

import savvats.utils.ControllerReportsWorker;
@Controller
@RequestMapping("/AlllocationReport")
public class AllLocationReportController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLocReportForm() {
		ModelAndView model = new ModelAndView("reports/AllReport");
		AllReport report = ControllerReportsWorker.AllReport();
		model.addObject("report", report);
		model.addObject("page", "allreport");
		return model;
	}
}
