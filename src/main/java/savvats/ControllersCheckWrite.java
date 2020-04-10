package savvats;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.ExDocDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import models.Box;
import models.ExDoc;
import models.Instrument;
import models.Location;

public class ControllersCheckWrite {
	static ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

	public static String addLocationWork(Location location) {
		StringBuilder errorText = new StringBuilder("<ul>");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		Location loc = locDAO.getLocByName(location.getName());
		boolean error = false;
		if (locDAO.hasError()) {
			error = true;
			errorText.append("<li> ошыбка бази данних </li>");
		} else {
			if (loc != null) {
				error = true;
				errorText.append("<li> место хранения существует </li>");
			}
		}
		if (!error) {
			
			if (!locDAO.createLocation(location)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
			else {
				if(location.isBoxes()==false) {
					Box box = new Box(0, locDAO.getLocByName(location.getName()));
					BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
					boxDAO.createBox(box);
				}
			}
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "место хранения успешно создано";
		} else {
			return errString;
		}

	}

	public static String addInstrumentWork(Instrument ininstr) {
		StringBuilder errorText = new StringBuilder("<ul>");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		boolean error = false;
		
		Instrument instrum = instDAO.getInstrumentByName(ininstr.getName());
		if (instDAO.hasError()) {
			error = true;
			errorText.append("<li> ошыбка бази данних </li>");
		} else {
			if (instrum != null) {
				error = true;
				errorText.append("<li> инструмент с таким именем уже существует </li>");
			}
		}
		if (!error) {
			if (!instDAO.createInstrument(ininstr)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "Инструмент успешно создан";
		} else {
			return errString;
		}
	}
	
	public static String addBoxWork(BoxListLocation box) {
		StringBuilder errorText = new StringBuilder("<ul>");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		boolean error = false;

		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		try {
			long locId = Long.parseLong(box.getLocationWB());
			Location loc = locDAO.getLocById(locId);
			if (loc == null) {
				error = true;
				errorText.append("<li> неправильное место хранения </li>");
			} else {
				box.setLocation(loc);
				Box tempBox = boxDAO.getBoxByNumber(box.getNumber(), box.getLocation().getId());
				if (tempBox != null) {
					error = true;
					errorText.append("<li> ячейка с таким номером уже существует </li>");
				}
				if (!box.getLocation().isBoxes()) {
					error = true;
					errorText.append("<li> место хранения не может содержать ячейки </li>");
				}
			}
		} catch (Exception e) {
			error = true;
			errorText.append("<li>неправильное место хранения</li>");
		}

		if (!error) {
			if (!boxDAO.createBox(box)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "Ячейка успесно создана";
		} else {
			return errString;
		}

	}

	public static String createExDocUnwrap(ExDocWEBList docListWrap) {
		List<ExDocWEB> docList = docListWrap.getDocList();
		String messages = null;
		for (int i = 0; i < docList.size(); i++) {

			messages = messages + makeExDoc(docList.get(i), i);
		}
		return messages;

	}

	

	public static String makeExDoc(ExDocWEB docW,int number ) {
		boolean error = false;
		StringBuilder errorText = new StringBuilder("<ul>");
		ExDoc doc = new ExDoc();
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		
		Location location = locDAO.getLocById(Long.parseLong(docW.getInLocation()));
		doc.setInLocation(location);
		Box box = boxDAO.getBoxByNumber(docW.getInBox(), location.getId());
		if(box==null) {
			error=true;
			errorText.append("<li>неправильная принимающая ячейка </li>");
		}
		else {
			doc.setInBox(box);
		}
				
		location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
		doc.setOutLocation(location);
		box = boxDAO.getBoxByID(docW.getOutBox());
		if(box==null) {
			error=true;
			errorText.append("<li>неправильная видающая ячейка </li>");
		}
		else {
			doc.setOutBox(box);
		}
		
		Instrument instrument = instDAO.getInstrumentByID(Long.parseLong(docW.getInstrument()));
		doc.setInstrument(instrument);
		
		doc.setAmount(docW.getAmount());
		
		errorText.append("</ul>");

		return null;

	}

	public static String addExDocWork(ExDoc doc) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ExDocDAO exDocDAO = (ExDocDAO) context.getBean("ExDocDAO");

		/*
		 * if (box.getNumber() == 0) { error = true;
		 * errorText.append("<li> не можеть бить  нуловой номер </li>"); }
		 * 
		 * if (box.getLocation() == null) { error = true;
		 * errorText.append("<li> не вибрано место хранения </li>"); } else {
		 * LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO"); Location
		 * loc = locDAO.getLocByName(box.getLocation().getName()); if (loc == null) {
		 * error = true; errorText.append("<li> неправильное место хранения </li>"); }
		 * Box tempBox = boxDAO.getBoxByNumber(box.getNumber(),
		 * box.getLocation().getId()); if (tempBox != null) { error = true;
		 * errorText.append("<li> ячейка с таким номером уже существует </li>"); } if
		 * (!box.getLocation().isBoxes()) { error = true;
		 * errorText.append("<li> место хранения не может содержать ячейки </li>"); } }
		 * 
		 * if (!error) { if (!boxDAO.createBox(box)) { error = true;
		 * errorText.append("<li>ошыбка бази данних </li>"); } }
		 */
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "Ячейка успесно создана";
		} else {
			return errString;
		}

	}
}
