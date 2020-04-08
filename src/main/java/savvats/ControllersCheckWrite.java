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
	public static String createExDocUnwrap(ExDocWEBList docListWrap) {
		List<ExDocWEB> docList = docListWrap.getDocList();
		String messages = null;
			for( int i=0;i<docList.size();i++) {
				
			messages = messages + checkExDocWeb(docList.get(i),i);
		}
		return messages;
		
	}
	public static String  checkExDocWeb(ExDocWEB docW, int number) {
		number++;
		StringBuilder errorText = new StringBuilder("<ul>");
		boolean error = false;
		if(docW.getInLocation()==null) {
			error=true;
			errorText.append("<li> не заполнено место хранения видачи  в строке " + number + " </li>");
		}
		if(docW.getOutLocation()==null) {
			error=true;
			errorText.append("<li> не заполнено место хранения приема в строке " + number + "</li>");
		}
	/*	if(docW.getInBox()==null) {
			error=true;
			errorText.append("<li> не заполнена ячейка видачи в строке " + number + "</li>");
		}
		if(docW.getOutBox()==null) {
			error=true;
			errorText.append("<li> не заполнена ячейка приема в строке " + number + "</li>");
		}
	*/
		if(docW.getAmount()==0) {
			error=true;
			errorText.append("<li> не заполнено количество в строке " + number + "</li>");
		}
		if(docW.getInstrument()==null) {
			error=true;
			errorText.append("<li> не вибран иструмент в строке " + number + " </li>");
		}
		if(error) {
			errorText.append("</ul>");
			return errorText.toString();
		}
		errorText.append(makeExDoc(docW));
		return errorText.toString();
	}
	
	public static String makeExDoc(ExDocWEB docW) {
		ExDoc doc =new ExDoc();
		//doc.set
		
		
		return null;
		
	}
	
	@SuppressWarnings("resource")
	public static String addExDocWork(ExDoc doc) {
		StringBuilder errorText = new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ExDocDAO exDocDAO = (ExDocDAO) context.getBean("ExDocDAO");
		
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
