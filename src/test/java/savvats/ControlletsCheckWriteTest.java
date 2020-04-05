package savvats;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.LocationDAO;
import models.Box;
import models.Instrument;
import models.Location;

class ControlletsCheckWriteTest {

	@Test
	void addLocationWork() {
		Location location = new Location();
		location.setName("tes");
		location.setBoxes(false);
		String message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("<ul><li> короткое имя </li></ul>"), "must be shor name");
		location.setName("test1");
		location.setBoxes(false);
		message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("<ul><li> место хранения существует </li></ul>"), "placae is exist");
		// check that name is not exist in db
		location.setName("test1loc3");
		location.setBoxes(true);
		message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("место хранения успешно создано"), "sucsecfuly created");

	}

	@Test
	void addInstrumentWork() {
		Instrument inst = new Instrument("tes", "шт");
		String message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("<ul><li> короткое имя </li></ul>"), "must be shor name");
		inst.setName("test1");
		message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("<ul><li> инструмент с таким именем уже существует </li></ul>"), "placae is exist");
		// check that name is not exist in db
		inst.setName("testInst1");
		message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("Инструмет успешно создан"), "sucsecfuly created");
	}
	@SuppressWarnings("resource")
	@Test
	void addBoxWork() {
		Box  box = new Box(1, null);
		String message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> не вибрано место хранения </li></ul>"), "must be short name");
		
		Location location = new Location();
		location.setName("test");
		location.setBoxes(true);
		location.setId(1);
		box.setLocation(location);
		box.setNumber(3);
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> неправильное место хранения </li></ul>"), "wrong place");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		List<Location> locationList= locDAO.getAllLocatin();
		locationList.get(0);
		box.setLocation(locationList.get(0));
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> место хранения не может содержать ячейки </li></ul>"), "place is not contein boxes");
		
		locationList= locDAO.getAllLocatinWB();
		locationList.get(0);
		box.setNumber(0);
		box.setLocation(locationList.get(0));
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> не можеть бить  нуловой номер </li></ul>"), "box created");
		
		locationList= locDAO.getAllLocatinWB();
		locationList.get(0);
		box.setNumber(5);
		box.setLocation(locationList.get(0));
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("Ячейка успесно создана"), "box created");
		
		
		
		
		
	}

}
