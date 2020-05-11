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

import dao.InstrumentDAO;

import models.Instrument;

import savvats.InstrumentReport;

import savvats.utils.ControllerReportsWorker;

@Controller
@RequestMapping("/instrumentReport")
public class InstrumentRepotrController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInstReportForm() {
		ModelAndView model = new ModelAndView("reports/InstrumentReport");
		model.addObject("report", new InstrumentReport());
		model.addObject("page", "instReport");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generateInstReport(@ModelAttribute("report") InstrumentReport instrumentReport ) {
		ModelAndView model = new ModelAndView("reports/InstrumentReport");
		long instId = instrumentReport.getId();
		instrumentReport.setLocReport( ControllerReportsWorker.getBoxInstrum(instId));
		model.addObject("report", instrumentReport);
		model.addObject("page", "instReport");
		return model;
	}
	
	@SuppressWarnings("resource")
	@ModelAttribute("instrumentMap")
	private Map<Long, String> getInstrumentMap() {
		Map<Long, String> instrumentMap = new HashMap<Long, String>();
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		List<Instrument> instList = instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}
}
