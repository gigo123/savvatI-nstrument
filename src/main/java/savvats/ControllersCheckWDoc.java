package savvats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.BoxDAO;
import dao.DocCatalogDAO;
import dao.DocDAO;
import dao.InstrumentDAO;
import dao.LocationDAO;
import dao.StorageDAO;
import models.Box;
import models.DocModel;
import models.ExDoc;
import models.ExDocCatalog;
import models.InDoc;
import models.InDocCatalog;
import models.Instrument;
import models.Location;
import models.OutDoc;
import models.OutDocCatalog;
import models.Storage;

public class ControllersCheckWDoc {
	static ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
	static LocationDAO locDAO;
	static InstrumentDAO instDAO;
	static BoxDAO boxDAO;
	static StorageDAO storageDAO;
	static DocDAO docDAO;
	static DocCatalogDAO exDocCatalogDAO;
	static DocCatalogDAO inDocCatalogDAO;
	static DocCatalogDAO outDocCatalogDAO;

	public static void initDAO() {
		locDAO = (LocationDAO) context.getBean("LocationDAO");
		instDAO = (InstrumentDAO) context.getBean("InstrumentDAO");
		boxDAO = (BoxDAO) context.getBean("BoxDAO");
		storageDAO = (StorageDAO) context.getBean("StorageDAO");
		docDAO = (DocDAO) context.getBean("ExDocDAO");
		exDocCatalogDAO = (DocCatalogDAO) context.getBean("ExDocCatalogDAO");
		inDocCatalogDAO = (DocCatalogDAO) context.getBean("InDocCatalogDAO");
		outDocCatalogDAO = (DocCatalogDAO) context.getBean("OutDocCatalogDAO");

	}

	public static void closeDAOConnection() {
		boxDAO.closeConection();
		locDAO.closeConection();
		instDAO.closeConection();
		storageDAO.closeConection();
		docDAO.closeConection();
		exDocCatalogDAO.closeConection();
		inDocCatalogDAO.closeConection();
		outDocCatalogDAO.closeConection();
	}

	public static String createExDocUnwrap(ExDocWEBList docListWrap, DocType docType) {
		List<ExDocWEB> docList = docListWrap.getDocList();
		StringBuilder errorText = new StringBuilder("<ul>");
		initDAO();
		List<ExDocTempStore> docTempList = new ArrayList<ExDocTempStore>();
		for (int i = 0; i < docList.size(); i++) {
			ExDocTempStore tempDoc = makeExDoc(docList.get(i), i, docType);
			errorText.append(tempDoc.getErrorString());
			docTempList.add(makeExDoc(docList.get(i), i, docType));
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			String error = writeExDocCatolog(docType);
			if (!error.equals("<li>ошыбка бази данних </li>")) {
				long catalogId = 0;
				if (docType == DocType.EXDOC) {
					catalogId = exDocCatalogDAO.getExDocCatalogBySnumber(error).getId();
				}
				if (docType == DocType.OUTDOC) {
					catalogId = outDocCatalogDAO.getExDocCatalogBySnumber(error).getId();
				}
				if (docType == DocType.INDOC) {
					catalogId = inDocCatalogDAO.getExDocCatalogBySnumber(error).getId();
				}
				for (ExDocTempStore exDocTempStore : docTempList) {
					error += writeExDoc(exDocTempStore.getDoc(), catalogId, exDocTempStore.getOutStorageId(), docType);
				}
			}
			closeDAOConnection();
			return error;
		} else {
			closeDAOConnection();
			return errString;
		}

	}

	public static ExDocTempStore makeExDoc(ExDocWEB docW, int number, DocType docType) {
		String errorText = "";
		number++;
		DocModel doc = null;
		ExDocTempStore tempDoc = null;
		if (docType == DocType.EXDOC) {
			doc = new ExDoc();
			tempDoc = checkInParam(docW, number, (ExDoc) doc);
			errorText = tempDoc.getErrorString();
			tempDoc = checkOutParam(docW, number, tempDoc.getDoc());
			errorText += tempDoc.getErrorString();
		}
		if (docType == DocType.INDOC) {
			doc = new InDoc();
			tempDoc = checkOutParam(docW, number, doc);
			errorText += tempDoc.getErrorString();
		}
		if (docType == DocType.OUTDOC) {
			doc = new OutDoc();
			tempDoc = checkOutParam(docW, number, doc);
			errorText += tempDoc.getErrorString();
		}
		if (errorText.equals("")) {
			tempDoc = checkInstrument(docW, number, tempDoc.getDoc(), docType);
			errorText += tempDoc.getErrorString();
			if (errorText.equals("")) {
				tempDoc.getDoc().setAmount(docW.getAmount());
				errorText = tempDoc.getErrorString();
				if (docType == DocType.EXDOC) {
					errorText += checkBox((ExDoc) tempDoc.getDoc());
				}
				tempDoc.setErrorString(errorText);
				return tempDoc;
			} else {
				return new ExDocTempStore(errorText, doc, 0);
			}
		} else {
			return new ExDocTempStore(errorText, doc, 0);
		}
	}

	private static String checkBox(ExDoc doc) {
		long inID = doc.getInBox().getId();
		long outId = doc.getOutBox().getId();
		if (inID == outId) {
			return "<li>одинаковие  ячейки приема и видачи</li>";
		}
		return "";

	}

	private static ExDocTempStore checkInstrument(ExDocWEB docW, int number, DocModel doc, DocType docType) {
		StringBuilder errorText = new StringBuilder("");
		Box box = doc.getOutBox();
		long storageId = 0;
		Instrument instrument = instDAO.getInstrumentByID(Long.parseLong(docW.getInstrument()));
		if (instrument == null) {
			errorText.append("<li>не правильний инструмент в строке " + number + " </li>");
		} else {
			if (docType == DocType.EXDOC || docType == DocType.OUTDOC) {
				List<Storage> storeList = storageDAO.getStorageByBox(box.getId());
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
					float instLeft = storage.getAmount() - docW.getAmount();
					if (instLeft > 0.0) {
						doc.setInstrument(instrument);
					} else {
						errorText.append("<li>недостачно инструмента для видачи  в строке " + number + "</li>");
					}

				} else {
					errorText.append("<li>нет инструмента в ячеке видачи  в строке " + number + "</li>");
				}

			}
			if (docType == DocType.INDOC) {
				doc.setInstrument(instrument);
			}
		}
		ExDocTempStore tempDoc = new ExDocTempStore(errorText.toString(), doc, storageId);
		return tempDoc;
	}

	private static ExDocTempStore checkInParam(ExDocWEB docW, int number, ExDoc doc) {
		String errorText = "";
		try {
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
		} catch (Exception e) {
			errorText = "<li>неправильное место приема в стоке " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}

	private static ExDocTempStore checkOutParam(ExDocWEB docW, int number, DocModel doc) {
		String errorText = "";
		try {
			Location location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
			if (location != null) {
				doc.setOutLocation(location);
				Box box = boxDAO.getBoxByID(docW.getOutBox());
				if (box == null) {
					errorText = "<li>неправильная видающая ячейка в строке " + number + "</li>";
				} else {
					doc.setOutBox(box);
				}
			} else {
				errorText = "<li>неправильное место  видачи в стоке " + number + "</li>";
			}
		} catch (Exception e) {
			errorText = "<li>неправильное место видачи в стоке " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}

	public static String writeExDoc(DocModel doc, long catId, long outStorageId, DocType docType) {
		try {

			long inStorageId = 0;
			Storage storage = null;
			List<Storage> storeList = null;
			float amount;
			ExDoc exDoc = null;
			if (docType == DocType.EXDOC || docType == DocType.INDOC) {

				if (docType == DocType.EXDOC) {
					exDoc = (ExDoc) doc;
					storage = storageDAO.getStorageByID(outStorageId);
					amount = storage.getAmount() - doc.getAmount();
					if (amount <= 0.0) {
						storageDAO.deleteStorage(outStorageId);
					} else {
						storage.setAmount(amount);
						storageDAO.updateStorage(outStorageId, storage);
					}
					storeList = storageDAO.getStorageByBox(exDoc.getInBox().getId());
					doc.setCatalogId(exDocCatalogDAO.getExDocCatalogById(catId));
				}
				if (docType == DocType.INDOC) {
					storeList = storageDAO.getStorageByBox(doc.getOutBox().getId());
					doc.setCatalogId(inDocCatalogDAO.getExDocCatalogById(catId));
				}
				Instrument instrument = doc.getInstrument();
				boolean hasInstrument = false;
				for (int i = 0; i < storeList.size(); i++) {
					Instrument tempInst = storeList.get(i).getInstrument();
					if (tempInst != null) {
						if (tempInst.getId() == instrument.getId()) {
							hasInstrument = true;
							inStorageId = storeList.get(i).getId();
							break;
						}
					}
				}
				if (hasInstrument) {
					storage = storageDAO.getStorageByID(inStorageId);
					amount = storage.getAmount() + doc.getAmount();
					storage.setAmount(amount);
					storageDAO.updateStorage(inStorageId, storage);
				} else {
					if (docType == DocType.EXDOC) {
						Box box = doc.getOutBox();
						List<Storage> tempStoreList = storageDAO.getStorageByBox(box.getId());
						if (tempStoreList.size() == 0) {
							box.setNotEmpty(true);
							boxDAO.updateBox(box.getId(), box);
						}
						Storage newInStorage = new Storage(exDoc.getInBox(), instrument, doc.getAmount());
						storageDAO.createStorage(newInStorage);
					}
					if (docType == DocType.INDOC) {
						Storage newInStorage = new Storage(doc.getOutBox(), instrument, doc.getAmount());
						storageDAO.createStorage(newInStorage);
					}

				}
				float instumentNumber = instrument.getTotalNumber() + doc.getAmount();
				instrument.setTotalNumber(instumentNumber);
				instDAO.updateInstrument(instrument);
			}
			if (docType == DocType.OUTDOC) {
				doc.setCatalogId(outDocCatalogDAO.getExDocCatalogById(catId));
				storage = storageDAO.getStorageByID(outStorageId);
				amount = storage.getAmount() - doc.getAmount();
				if (amount <= 0.0) {
					storageDAO.deleteStorage(outStorageId);
					Box box = doc.getOutBox();
					List<Storage> tempStoreList = storageDAO.getStorageByBox(box.getId());
					if (tempStoreList.size() == 0) {
						box.setNotEmpty(false);
						boxDAO.updateBox(box.getId(), box);
					}
				} else {
					storage.setAmount(amount);
					storageDAO.updateStorage(outStorageId, storage);
				}

				float instumentNumber = doc.getInstrument().getTotalNumber() - doc.getAmount();
				doc.getInstrument().setTotalNumber(instumentNumber);
				instDAO.updateInstrument(doc.getInstrument());
			}
			docDAO.createExDoc(doc, docType);
		} catch (

		Exception e) {
			return "<li>ошыбка бази данних </li>";
		}
		return "документ создан";

	}

	public static String writeExDocCatolog(DocType docType) {
		StringBuilder errorText = new StringBuilder("");
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		String numberString = null;
		if (docType == DocType.EXDOC) {
			List<Integer> numberList = exDocCatalogDAO.getExDocCatalogByYearN(year);
			Collections.sort(numberList);
			int lastNumber;
			if (numberList.size() == 0) {
				lastNumber = 1;
			} else {
				lastNumber = numberList.get(numberList.size() - 1);
				lastNumber++;
			}
			numberString = "" + year + "-" + lastNumber;
			ExDocCatalog exCat = new ExDocCatalog(year, lastNumber, numberString, date);

			if (!exDocCatalogDAO.createExDocCatalog(exCat)) {
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		if (docType == DocType.INDOC) {
			List<Integer> numberList = inDocCatalogDAO.getExDocCatalogByYearN(year);
			Collections.sort(numberList);
			int lastNumber;
			if (numberList.size() == 0) {
				lastNumber = 1;
			} else {
				lastNumber = numberList.get(numberList.size() - 1);
				lastNumber++;
			}
			numberString = "" + year + "-" + lastNumber;

			InDocCatalog inCat = new InDocCatalog(year, lastNumber, numberString, date);

			if (!inDocCatalogDAO.createExDocCatalog(inCat)) {
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		if (docType == DocType.OUTDOC) {
			List<Integer> numberList = outDocCatalogDAO.getExDocCatalogByYearN(year);
			Collections.sort(numberList);
			int lastNumber;
			if (numberList.size() == 0) {
				lastNumber = 1;
			} else {
				lastNumber = numberList.get(numberList.size() - 1);
				lastNumber++;
			}
			numberString = "" + year + "-" + lastNumber;

			OutDocCatalog inCat = new OutDocCatalog(year, lastNumber, numberString, date);

			if (!outDocCatalogDAO.createExDocCatalog(inCat)) {
				errorText.append("<li>ошыбка бази данних </li>");
			}
		}
		String errString = errorText.toString();
		if (errString.equals("")) {
			return numberString;
		} else {
			return errString;
		}

	}
}
