package savvats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Instrument;
import models.Location;

class ControlletsCheckWriteTest {

	@Test
	void addLocationWork() {
		Location location = new Location();
		location.setName("tes");
		location.setBoxes(false);
		String message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("<ul><li> короткое имя </li></ul>"),"must be shor name");
		location.setName("test1");
		location.setBoxes(false);
		 message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("<ul><li> место хранения существует </li></ul>"),"placae is exist");
		// check that name is not exist in db
		location.setName("test1loc3");
		location.setBoxes(true);
		 message = ControllersCheckWrite.addLocationWork(location);
		assertTrue(message.equals("место хранения успешно создано"),"sucsecfuly created");
	
	}
	@Test
	void addInstrumentWork() {
		Instrument inst = new Instrument("tes", "шт");
		String message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("<ul><li> короткое имя </li></ul>"),"must be shor name");
		inst.setName("test1");
		 message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("<ul><li> инструмент с таким именем уже существует </li></ul>"),"placae is exist");
		// check that name is not exist in db
		inst.setName("testInst1");

		 message = ControllersCheckWrite.addInstrumentWork(inst);
		assertTrue(message.equals("Инструмет успешно создан"),"sucsecfuly created");
	
	}

}
