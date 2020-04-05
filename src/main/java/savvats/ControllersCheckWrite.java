package savvats;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.LocationDAO;
import models.Location;

public class ControllersCheckWrite {

	@SuppressWarnings("resource")
	public  static String  addLocationWork(Location location) {
		StringBuilder errorText =  new StringBuilder("<ul>");
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		Location loc = locDAO.getLocByName(location.getName());
		boolean error=false;
		if (location.getName().length() < 4) {
			error=true;
			errorText.append("<li> короткое имя </li>");
		}
		if (locDAO.hasError()) {
			error=true;
			errorText.append("<li> ошыбка бази данних </li>");
		} else {
			if (loc != null) {
				error=true;
				errorText.append("<li> место хранения существует </li>");
			}
		}
		if(!error) {
		if (!locDAO.createLocation(location)) {
			error = true;
			errorText.append("<li>ошыбка бази данних </li>");
		}
		}
		String errString = errorText.toString();
		if(errString.equals("<ul>")) {
			return "успешно создано";
		}
		else {
			return errString;
		}
		
	}
}
