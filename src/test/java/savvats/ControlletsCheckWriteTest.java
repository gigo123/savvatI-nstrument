package savvats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

}
