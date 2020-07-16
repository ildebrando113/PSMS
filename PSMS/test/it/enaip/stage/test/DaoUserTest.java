package it.enaip.stage.test;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.enaip.stage.controller.UserController;
import it.enaip.stage.dao.DaoUser;
import it.enaip.stage.dao.DataSourceFactory;
import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;

//OrderAnnotation.class

@TestMethodOrder(OrderAnnotation.class)


class DaoUserTest {
	
	private DaoUser daoUser = DaoUser.getInstance();
	private User user;
	
	@BeforeEach
	void setUp() throws Exception {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", "test_surname", date, creationtime, 19, Status.C);
	}
	
	@Test
	@Order(1)
	void testBbConn() {
		Connection conn = DataSourceFactory.getConnection();
		assertEquals(conn!= null, true);
	}

	@Test
	@Order(2)
	void canSaveNewUser() throws ParseException, SQLException {
		boolean expected = daoUser.save(user);
		assertEquals(true, expected);		
	}
	
	/*
	@Test
	@Order(3)
	void canUpdateUser() throws ParseException, SQLException {
		userOk.setName("test_name_modified");
//		userOk.setId(userOk.getId());
        boolean expected = daoUser.update(userOk);
		assertEquals(true, expected);		
	}
	*/
	@Test
	@Order(4)
	public void testRecordIsPresent() throws JSONException, SQLException {
		UserController controller = new UserController();
		JSONObject jobj = controller.getJson("2");
		assertTrue(jobj.length() > 0);
		assertTrue(jobj.has("id"));
		assertTrue(jobj.has("name"));
		assertTrue(jobj.has("surname"));
		assertTrue(jobj.has("birthDate"));
		assertTrue(jobj.has("age"));
		assertTrue(jobj.has("type"));
		assertTrue(jobj.has("creationtime"));
	}

	@Test
	@Order(5)
	void canFindUser() throws SQLException {

	boolean expected = false;
	User test = daoUser.findUser(user.getId());
	
	
	if (test.getId()==user.getId()) {
		expected=true;
	}
	
	assertEquals(true, expected);		
	}

	/*
	@Test
	@Order(6)
    void canFindAll() throws SQLException {
		 boolean expected = false;
		 List<User> listuserOk= new ArrayList<>();
		 listuser=daoUser.findAll();
		 int index = daoUser.getMaxIndex();
		 index--;
		 User test=listuser.get(index);
		 if (test.getId()==userOk.getId()) {
			 expected= true ;
		 }
		 assertEquals(true,expected);
		 
	}
	*/

	@Test
	@Order(6)

	void canDeleteUser() throws ParseException, SQLException {
        boolean expected = daoUser.delete(user);
		assertEquals(true, expected);		
	}
	
	@Test
	void cannotSaveNewUserWithEmptyName() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "", "test_surname", date, creationtime, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
	@Test
	void cannotSaveNewUserWithNullName() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), null, "test_surname", date, creationtime, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
	@Test
	void cannotSaveNewUserWithEmptySurname() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", "", date, creationtime, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
	@Test
	void cannotSaveNewUserWithNullSurname() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", null, date, creationtime, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}

	@Test
	void cannotSaveBirthdateNullDate() throws ParseException, SQLException {
//		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", "test_surname", null, creationtime, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
	@Test
	void cannotSaveCreationtimeNullDate() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
//		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", "test_surname", date, null, 19, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
//	The field Age does not allow the user to type anything else than a number
//	@Test
//	void cannotSaveNewUserWithNullAge() throws ParseException, SQLException {
//		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
//		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
//		user = new User(daoUser.getMaxIndex(), "test_name", "test_surname", date, creationtime, 0, Status.C);
//		
//		Assertions.assertThrows(IllegalArgumentException.class, () -> {
//			daoUser.save(user);
//		  });
//	
//	}
	@Test
	void cannotSaveNewUserWithNegativeAge() throws ParseException, SQLException {
		Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2001");	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		user = new User(daoUser.getMaxIndex(), "test_name", "test_surname", date, creationtime, -10, Status.C);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			daoUser.save(user);
		  });
	
	}
}

