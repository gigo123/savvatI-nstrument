package savvats;


import java.util.ArrayList;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.StorageDAO;
import models.Box;
import models.Storage;

public class ReportsWorker {
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
		System.out.println(boxReport);
		return boxReport;
		
		
	}

}
