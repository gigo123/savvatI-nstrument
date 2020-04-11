package savvats;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import dao.StorageDAO;
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
		assertTrue(message.equals("Инструмент успешно создан"), "sucsecfuly created");
	}

	@Test
	void addBoxWork() {
		BoxListLocation box = new BoxListLocation();
		box.setLocationWB("2");
		box.setNumber(8);
		String message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> неправильное место хранения </li></ul>"), "wrong place");
		box.setLocationWB("1");
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> место хранения не может содержать ячейки </li></ul>"),
				"place is not contein boxes");
		// put number that exist
		box.setNumber(5);
		box.setLocationWB("7");
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("<ul><li> ячейка с таким номером уже существует </li></ul>"),
				"box with that number exist");
		// put number that not exist
		box.setNumber(10);
		message = ControllersCheckWrite.addBoxWork(box);
		assertTrue(message.equals("Ячейка успесно создана"), "box created");
	}

	@Test
	void writeExDocCatolog() {
		String message = ControllersCheckWrite.writeExDocCatolog();
		assertTrue(message.equals("документ успешно создан"), "box created");

	}

	@Test
	void makeExDoc() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		LocationDAO locDAO = (LocationDAO) context.getBean("LocationDAO");
		InstrumentDAO instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		BoxDAO boxDAO = (BoxDAO) context.getBean("BoxDAO");
		StorageDAO storageDAO = (StorageDAO) context.getBean("StorageDAO");
		
		ExDocWEB docW = new ExDocWEB("1", "1", 1, 1, "2", 1);
		ExDocTempStore exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		String message = exDocTempStore.getErrorString();
		assertTrue(message.equals(""), "no errors");

		docW = new ExDocWEB("50", "1", 1, 1, "2", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место  видачи в стоке 1</li>"), "wrong outlocation");

		docW = new ExDocWEB("1", "50", 1, 1, "2", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место приема в стоке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 50, 1, "2", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная видающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 50, "2", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная принимающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "50", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>не правильний инструмент в строке 1 </li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "5", 1);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>нет инструмента в ячеке видачи  в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "2", 11);
		exDocTempStore = ControllersCheckWrite.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>недостачно инструмента для видачи  в строке 1</li>"), "wrong inlocation");
		
		boxDAO.closeConection();
		locDAO.closeConection();
		instDAO.closeConection();
		storageDAO.closeConection();
	}

}
