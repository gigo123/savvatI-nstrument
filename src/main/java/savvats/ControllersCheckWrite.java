package savvats;

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

	@SuppressWarnings("resource")
	public static String addLocationWork(Location location) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		Location loc = locDAO.getLocByName(location.getName());
		boolean error = false;
		if (location.getName().length() < 4) {
			error = true;
			errorText.append("<li> короткое имя </li>");
		}
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
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "место хранения успешно создано";
		} else {
			return errString;
		}

	}

	@SuppressWarnings("resource")
	public static String addInstrumentWork(Instrument ininstr) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		boolean error = false;
		if (ininstr.getName().length() < 4) {
			error = true;
			errorText.append("<li> короткое имя </li>");
		}
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
			return "Инструмет успешно создан";
		} else {
			return errString;
		}
	}

	@SuppressWarnings("resource")
	public static String addBoxWork(Box box) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		boolean error = false;
		if (box.getNumber() == 0) {
			error = true;
			errorText.append("<li> не можеть бить  нуловой номер </li>");
		}

		if (box.getLocation() == null) {
			error = true;
			errorText.append("<li> не вибрано место хранения </li>");
		} else {
			LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
			Location loc = locDAO.getLocByName(box.getLocation().getName());
			if (loc == null) {
				error = true;
				errorText.append("<li> неправильное место хранения </li>");
			}
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
	@SuppressWarnings("resource")
	public static String addExDocWork(ExDoc doc) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ExDocDAO exDocDAO = (ExDocDAO) context.getBean("ExDocDAO");
		boolean error = false;
		if(doc.getInLocation()==null) {
			error=true;
		}
		if(doc.getOutLocation()==null) {
			error=true;
		}
		if(doc.getInBox()==null) {
			error=true;
		}
		if(doc.getOutBox()==null) {
			error=true;
		}
		if(doc.getAmount()==0) {
			error=true;
		}
		if(doc.getInstrument()==null) {
			error=true;
		}
		/*if (box.getNumber() == 0) {
			error = true;
			errorText.append("<li> не можеть бить  нуловой номер </li>");
		}

		if (box.getLocation() == null) {
			error = true;
			errorText.append("<li> не вибрано место хранения </li>");
		} else {
			LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
			Location loc = locDAO.getLocByName(box.getLocation().getName());
			if (loc == null) {
				error = true;
				errorText.append("<li> неправильное место хранения </li>");
			}
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

		if (!error) {
			if (!boxDAO.createBox(box)) {
				error = true;
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
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
