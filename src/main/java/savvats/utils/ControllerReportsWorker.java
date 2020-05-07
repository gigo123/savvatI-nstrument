package savvats.utils;


import java.util.ArrayList;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.LocationDAO;
import dao.StorageDAO;
import models.Box;
import models.Location;
import models.Storage;
import savvats.AllReport;
import savvats.BoxReport;
import savvats.LocationReport;
import savvats.ReportItem;

public class ControllerReportsWorker {
	@SuppressWarnings("resource")
	public static BoxReport getInstInBox(long boxId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		Box box =boxDAO.getBoxByID(boxId);
		BoxReport boxReport = null;
		if(box!=null) {
			
		List<Storage> storeList = storeDAO.getStorageByBox(boxId);
		List<ReportItem> reportItems = new ArrayList<ReportItem>();
		ReportItem reportItem = null;
		float tAmount =0;
		for (Storage storage : storeList) {
			 reportItem = new ReportItem();
			 tAmount +=storage.getAmount();
			 reportItem.setAmount(storage.getAmount());
			 reportItem.setName(storage.getInstrument().getName());
			 reportItem.setMeasure(storage.getInstrument().getMeasure());
			 reportItems.add(reportItem);
		} 
	
		 boxReport = new BoxReport(String.valueOf(box.getNumber())
				, tAmount, reportItems);
	}
		return boxReport;
		
		
	}
	
	@SuppressWarnings("resource")
	public static LocationReport getInstInLocation(long locationId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		Location location = locDAO.getLocById(locationId);
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		LocationReport locReport = null;
		if(location!=null) {
			
			float tAmount =0;
		List<Box> boxList = boxDAO.getAllBoxByLocation(locationId);
		List<BoxReport> boxReports = new ArrayList<BoxReport>();
		BoxReport boxReport= null;
		for (Box box : boxList) {
			boxReport = getInstInBox(box.getId());
			tAmount+=boxReport.getTotalAmount();
			boxReports.add(boxReport);
		}
		
		 locReport = new LocationReport(location.getName(), tAmount, boxReports);
		}
		return locReport;
		
	}
	@SuppressWarnings("resource")
	public static AllReport AllReport() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		List<Location> locList = locDAO.getAllLocatin();
		List<LocationReport> locReportList = new ArrayList<LocationReport>();
		float tAmount=0;
		LocationReport locReport = null;
		for (Location location : locList) {
			locReport =getInstInLocation(location.getId());
			locReportList.add(locReport);
			tAmount+=locReport.getTotalAmount();
		}
		AllReport report = new AllReport(locReportList, tAmount);
		return report;
		
	}

}
