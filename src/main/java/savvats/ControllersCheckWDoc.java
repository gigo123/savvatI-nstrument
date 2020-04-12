package savvats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.ExDocCatalogDAO;
import dao.ExDocDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import dao.StorageDAO;
import models.Box;
import models.ExDoc;
import models.ExDocCatalog;
import models.Instrument;
import models.Location;
import models.Storage;

public class ControllersCheckWDoc {
	static ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
	static LocationDAO locDAO;
	static InstrumentDAO instDAO;
	static BoxDAO boxDAO;
	static StorageDAO storageDAO;
	static ExDocDAO exDocDAO;
	static ExDocCatalogDAO exDocCatalogDAO;

	public static void initDAO() {
		locDAO = (LocationDAO) context.getBean("LocationDAO");
		instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		boxDAO = (BoxDAO) context.getBean("BoxDAO");
		storageDAO = (StorageDAO) context.getBean("StorageDAO");
		exDocDAO = (ExDocDAO) context.getBean("ExDocDAO");
		exDocCatalogDAO = (ExDocCatalogDAO) context.getBean("ExDocCatalogDAO");

	}

	public static void closeDAOConnection() {
		boxDAO.closeConection();
		locDAO.closeConection();
		instDAO.closeConection();
		storageDAO.closeConection();
		exDocDAO.closeConection();
		exDocCatalogDAO.closeConection();
	}

	public static String createExDocUnwrap(ExDocWEBList docListWrap) {
		List<ExDocWEB> docList = docListWrap.getDocList();
		StringBuilder errorText = new StringBuilder("<ul>");
		initDAO();
		List<ExDocTempStore> docTempList = new ArrayList<ExDocTempStore>();
		for (int i = 0; i < docList.size(); i++) {
			ExDocTempStore tempDoc = makeExDoc(docList.get(i), i);
			errorText.append(tempDoc.getErrorString());
			docTempList.add(makeExDoc(docList.get(i), i));
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			String error = writeExDocCatolog();
			if (!error.equals("<li>ошыбка бази данних </li>")) {
				long catalogId = exDocCatalogDAO.getExDocCatalogBySnumber(error).getId();
				for (ExDocTempStore exDocTempStore : docTempList) {
					error += writeExDoc(exDocTempStore.getDoc(), catalogId, exDocTempStore.getOutStorageId());
				}
			}
			closeDAOConnection();
			return error;
		} else {
			closeDAOConnection();
			return errString;
		}

	}

	public static ExDocTempStore makeExDoc(ExDocWEB docW, int number) {
		String errorText = "";
		number++;
		ExDoc doc = new ExDoc();
		ExDocTempStore tempDoc;

		tempDoc = checkInParam(docW, number, doc);
		errorText = tempDoc.getErrorString();
		tempDoc = checkOutParam(docW, number, tempDoc.getDoc());
		errorText += tempDoc.getErrorString();
		
		if (errorText.equals("")) {
			tempDoc = checkInstrument(docW, number, doc);
			tempDoc.getDoc().setAmount(docW.getAmount());
			errorText= tempDoc.getErrorString();
			errorText +=checkBox(tempDoc.getDoc());
			tempDoc.setErrorString(errorText);
			return tempDoc;
		} else {
			return new ExDocTempStore(errorText, doc, 0);
		}
	}
	private static String checkBox(ExDoc doc) {
		long inID = doc.getInBox().getId();
		long outId = doc.getOutBox().getId();
		if(inID==outId) {
			return "<li>одинаковие  ячейки приема и видачи</li>";
		}
		return "";
		
	}
	private static ExDocTempStore checkInstrument(ExDocWEB docW, int number, ExDoc doc) {
		StringBuilder errorText = new StringBuilder("");
		Box box = doc.getOutBox();
		long storageId = 0;
		Instrument instrument = instDAO.getInstrumentByID(Long.parseLong(docW.getInstrument()));
		if (instrument == null) {
			errorText.append("<li>не правильний инструмент в строке " + number + " </li>");
		} else {

			List<Storage> storeList = storageDAO.getStorageByBox(box);
			boolean hasInstrument = false;
			for (int i = 0; i < storeList.size(); i++) {
				Instrument tempInst = storeList.get(i).getInstrument();
				if (tempInst != null) {
					if (tempInst.getId() == instrument.getId()) {
						hasInstrument = true;
						storageId = storeList.get(i).getId();
					}
				}
			}
			if (hasInstrument) {
				Storage storage = storageDAO.getStorageByID(storageId);
				if (storage.getAmount() >= docW.getAmount()) {
					doc.setInstrument(instrument);
				} else {
					errorText.append("<li>недостачно инструмента для видачи  в строке " + number + "</li>");
				}

			} else {
				errorText.append("<li>нет инструмента в ячеке видачи  в строке " + number + "</li>");
			}
		}
		ExDocTempStore tempDoc = new ExDocTempStore(errorText.toString(), doc, storageId);
		return tempDoc;
	}

	private static ExDocTempStore checkInParam(ExDocWEB docW, int number, ExDoc doc) {
		String errorText = "";
		Location location = locDAO.getLocById(Long.parseLong(docW.getInLocation()));
		if (location != null) {
			doc.setInLocation(location);
			Box box = boxDAO.getBoxByNumber(docW.getInBox(), location.getId());
			if (box == null) {
				errorText = "<li>неправильная принимающая ячейка в строке " + number + "</li>";
			} else {
				doc.setInBox(box);
			}
		} else {
			errorText = "<li>неправильное место приема в стоке " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}

	private static ExDocTempStore checkOutParam(ExDocWEB docW, int number, ExDoc doc) {
		String errorText = "";
		Location location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
		if (location != null) {
			doc.setInLocation(location);
			Box box = boxDAO.getBoxByNumber(docW.getOutBox(), location.getId());
			if (box == null) {
				errorText = "<li>неправильная видающая ячейка в строке " + number + "</li>";
			} else {
				doc.setOutBox(box);
			}
		} else {
			errorText = "<li>неправильное место  видачи в стоке " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}

	public static String writeExDoc(ExDoc doc, long catId, long outStorageId) {
		try {
			exDocDAO.createExDoc(doc);
			long inStorageId = 0;
			Storage storage = storageDAO.getStorageByID(outStorageId);
			float amount = storage.getAmount() - doc.getAmount();
			storage.setAmount(amount);
			storageDAO.updateStorage(outStorageId, storage);

			List<Storage> storeList = storageDAO.getStorageByBox(doc.getInBox());
			Instrument instrument = doc.getInstrument();
			boolean hasInstrument = false;
			for (int i = 0; i < storeList.size(); i++) {
				Instrument tempInst = storeList.get(i).getInstrument();
				if (tempInst != null) {
					if (tempInst.getId() == instrument.getId()) {
						hasInstrument = true;
						inStorageId = storeList.get(i).getId();
					}
				}
			}
			if (hasInstrument) {
				storage = storageDAO.getStorageByID(inStorageId);
				amount = storage.getAmount() + doc.getAmount();
				storage.setAmount(amount);
				storageDAO.updateStorage(inStorageId, storage);
			} else {
				Storage newInStorage = new Storage(doc.getInBox(), instrument, doc.getAmount());
				storageDAO.createStorage(newInStorage);
			}
		} catch (Exception e) {
			return "<li>ошыбка бази данних </li>";
		}
		return "";

	}

	public static String writeExDocCatolog() {
		StringBuilder errorText = new StringBuilder("");
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		List<Integer> numberList = exDocCatalogDAO.getExDocCatalogByYearN(year);
		Collections.sort(numberList);
		int lastNumber = numberList.get(numberList.size() - 1);
		lastNumber++;
		String numberString = "" + year + "-" + lastNumber;

		ExDocCatalog exCat = new ExDocCatalog(year, lastNumber, numberString, date);
		if (!exDocCatalogDAO.createExDocCatalog(exCat)) {
			errorText.append("<li>ошыбка бази данних </li>");
		}
		String errString = errorText.toString();
		if (errString.equals("")) {
			return numberString;
		} else {
			return errString;
		}

	}
}
