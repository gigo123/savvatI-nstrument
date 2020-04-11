package savvats;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import dao.StorageDAO;

public class ControllresCheckWDocTest {

	@Test
	void writeExDocCatolog() {
		String message = ControllersCheckWDoc.writeExDocCatolog();
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
		ExDocTempStore exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		String message = exDocTempStore.getErrorString();
		assertTrue(message.equals(""), "no errors");

		docW = new ExDocWEB("50", "1", 1, 1, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место  видачи в стоке 1</li>"), "wrong outlocation");

		docW = new ExDocWEB("1", "50", 1, 1, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место приема в стоке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 50, 1, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная видающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 50, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная принимающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "50", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>не правильний инструмент в строке 1 </li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "5", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>нет инструмента в ячеке видачи  в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 1, "2", 11);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1, locDAO, instDAO, boxDAO, storageDAO);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>недостачно инструмента для видачи  в строке 1</li>"), "wrong inlocation");
		
		boxDAO.closeConection();
		locDAO.closeConection();
		instDAO.closeConection();
		storageDAO.closeConection();
	}
	@Test
	void createExDocUnwrap() {
		ExDocWEBList docListWrap = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(new ExDocWEB("1", "1", 1, 1, "2", 1));
		docList.add(new ExDocWEB("50", "1", 1, 1, "2", 1));
		docList.add(new ExDocWEB("1", "50", 1, 1, "2", 1));
		docList.add(new ExDocWEB("1", "1", 50, 1, "2", 1));
		docList.add(new ExDocWEB("1", "1", 1, 50, "2", 1));
		docList.add(new ExDocWEB("1", "1", 1, 1, "1", 1));
		docList.add(new ExDocWEB("1", "1", 1, 1, "2", 11));
		docListWrap.setDocList(docList);
		String message =ControllersCheckWDoc.createExDocUnwrap(docListWrap);
		assertTrue(message.equals("<ul><li>неправильное место  видачи в стоке 2</li>"
				+ "<li>неправильное место приема в стоке 3</li>"
				+ "<li>неправильная видающая ячейка в строке 4</li>"
				+ "<li>неправильная принимающая ячейка в строке 5</li>"
				+ "<li>не правильний инструмент в строке 6 </li>"
				+ "<li>недостачно инструмента для видачи  в строке 7</li></ul>"),"six errors");
		
	}
}
