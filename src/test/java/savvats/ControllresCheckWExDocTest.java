package savvats;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import models.ExDoc;

public class ControllresCheckWExDocTest {

	@Test
	void writeExDocCatolog() {
		ControllersCheckWDoc.initDAO();
		String message = ControllersCheckWDoc.writeExDocCatolog(DocType.EXDOC);
		assertTrue(message.equals("документ успешно создан"), "box created");

	}

	@Test
	void makeExDoc() {
		ControllersCheckWDoc.initDAO();
		ExDocWEB docW = new ExDocWEB("1", "1", 1, 2, "2", 1);
		ExDocTempStore exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		String message = exDocTempStore.getErrorString();
		assertTrue(message.equals(""), "no errors");

		docW = new ExDocWEB("50", "1", 1, 2, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место  видачи в стоке 1</li>"), "wrong outlocation");

		docW = new ExDocWEB("1", "50", 1, 2, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильное место приема в стоке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 50, 2, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная видающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 50, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>неправильная принимающая ячейка в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 2, "50", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>не правильний инструмент в строке 1 </li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 2, "5", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>нет инструмента в ячеке видачи  в строке 1</li>"), "wrong inlocation");

		docW = new ExDocWEB("1", "1", 1, 2, "2", 11);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>недостачно инструмента для видачи  в строке 1</li>"), "wrong inlocation");
		
		docW = new ExDocWEB("1", "1", 1, 1, "2", 1);
		exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 0,DocType.EXDOC);
		message = exDocTempStore.getErrorString();
		assertTrue(message.equals("<li>одинаковие  ячейки приема и видачи</li>"), "wrong inlocation");
		
	}

	@Test
	void createExDocUnwrap() {
		ControllersCheckWDoc.initDAO();
		ExDocWEBList docListWrap = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(new ExDocWEB("1", "1", 1, 2, "2", 1));
		docList.add(new ExDocWEB("50", "1", 1, 2, "2", 1));
		docList.add(new ExDocWEB("1", "50", 1, 2, "2", 1));
		docList.add(new ExDocWEB("1", "1", 50, 1, "2", 1));
		docList.add(new ExDocWEB("1", "1", 1, 50, "2", 1));
		docList.add(new ExDocWEB("1", "1", 1, 2, "1", 1));
		docList.add(new ExDocWEB("1", "1", 1, 2, "2", 11));
		docListWrap.setDocList(docList);
		String message = ControllersCheckWDoc.createExDocUnwrap(docListWrap,DocType.EXDOC);
		assertTrue(message.equals("<ul><li>неправильное место  видачи в стоке 2</li>"
				+ "<li>неправильное место приема в стоке 3</li>" + "<li>неправильная видающая ячейка в строке 4</li>"
				+ "<li>неправильная принимающая ячейка в строке 5</li>"
				+ "<li>не правильний инструмент в строке 6 </li>"
				+ "<li>недостачно инструмента для видачи  в строке 7</li></ul>"), "six errors");

	}

	@Test
	void createExDocUnwrapAdd() {
		ControllersCheckWDoc.initDAO();
		ExDocWEBList docListWrap = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(new ExDocWEB("1", "1", 1, 2, "2", 1));

		docListWrap.setDocList(docList);
		String message = ControllersCheckWDoc.createExDocUnwrap(docListWrap,DocType.EXDOC);
		assertFalse(message.equals("2020"), "six errors"); // test mast fali

	}

	@Test
	void writeExDoc() {
		ControllersCheckWDoc.initDAO();
		ExDocWEB docW = new ExDocWEB("1", "1", 1, 1, "2", 1);
		ExDocTempStore exDocTempStore = ControllersCheckWDoc.makeExDoc(docW, 1,DocType.EXDOC);

		ExDoc doc = (ExDoc) exDocTempStore.getDoc();
		String message = ControllersCheckWDoc.writeExDoc(doc, 3, exDocTempStore.getOutStorageId(),DocType.EXDOC);
		System.out.println(message);
		assertTrue(message.equals(""), "no errors");
	}
}
