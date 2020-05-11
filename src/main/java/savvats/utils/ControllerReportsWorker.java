package savvats.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import dao.StorageDAO;
import models.Box;
import models.Instrument;
import models.Location;
import models.Storage;
import savvats.AllReport;
import savvats.BoxReport;
import savvats.LocationReport;
import savvats.ReportItem;
import savvats.ReportItemWLoc;

public class ControllerReportsWorker {
	@SuppressWarnings("resource")
	public static BoxReport getInstInBox(long boxId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		Box box = boxDAO.getBoxByID(boxId);
		float tAmount = 0;
		List<ReportItem> reportItems = new ArrayList<ReportItem>();
		if (box != null) {
			List<Storage> storeList = storeDAO.getStorageByBox(boxId);
			ReportItem reportItem = null;
			for (Storage storage : storeList) {
				reportItem = new ReportItem();
				if (storage.getAmount() != 0) {
					tAmount += storage.getAmount();
					reportItem.setAmount(storage.getAmount());
					reportItem.setName(storage.getInstrument().getName());
					reportItem.setMeasure(storage.getInstrument().getMeasure());
					reportItems.add(reportItem);
				}
			}
		}
		if (tAmount == 0) {
			return null;
		} else {
			return new BoxReport(String.valueOf(box.getNumber()), tAmount, reportItems);
		}

	}

	@SuppressWarnings("resource")
	public static LocationReport getInstInLocation(long locationId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		Location location = locDAO.getLocById(locationId);
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		float tAmount = 0;
		List<BoxReport> boxReports = new ArrayList<BoxReport>();
		if (location != null) {
			List<Box> boxList = boxDAO.getAllBoxByLocation(locationId);
			BoxReport boxReport = null;
			for (Box box : boxList) {
				boxReport = getInstInBox(box.getId());
				if (boxReport != null) {
					tAmount += boxReport.getTotalAmount();
					boxReports.add(boxReport);
				}
			}
		}
		if (tAmount == 0) {
			return null;
		} else {
			return new LocationReport(location.getName(), tAmount, boxReports);
		}

	}
	@SuppressWarnings("resource")
	public static List<ReportItemWLoc> getBoxInstrum(long instId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		StorageDAO storeDAO = (StorageDAO) context.getBean("StorageDAO");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		Instrument inst = instDAO.getInstrumentByID(instId);
	
		String measure = inst.getMeasure();
		List<Storage> storeList =storeDAO.getStorageByinstrument(instId);
		List<ReportItemWLoc> reportItems = new ArrayList<ReportItemWLoc>();
		for (Storage storage : storeList) {
			Box box = storage.getBox();
			String name = String.valueOf(box.getNumber());
			String locName = box.getLocation().getName();
			if (box != null) {
				ReportItemWLoc reportItem = new ReportItemWLoc(name, storage.getAmount(), measure,locName );
				reportItems.add(reportItem );
			}	
		}
		return reportItems;
		
	}

	@SuppressWarnings("resource")
	public static AllReport AllReport() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		List<Location> locList = locDAO.getAllLocatin();
		List<LocationReport> locReportList = new ArrayList<LocationReport>();
		float tAmount = 0;
		LocationReport locReport = null;
		for (Location location : locList) {
			locReport = getInstInLocation(location.getId());
			if (locReport.getTotalAmount() != 0) {
				locReportList.add(locReport);
				tAmount += locReport.getTotalAmount();
			}
		}
		AllReport report = new AllReport(locReportList, tAmount);
		return report;

	}

}
