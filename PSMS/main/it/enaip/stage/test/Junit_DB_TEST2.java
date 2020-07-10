package it.enaip.stage.test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.enaip.stage.controller.UserController;
import it.enaip.stage.dao.DaoUser;
import it.enaip.stage.dao.DataSourceFactory;
import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;


class Junit_DB_TEST2 {

	
	
	@Test
	void testBbConn() {
		System.out.println("get Connection");
		Connection conn = DataSourceFactory.getConnection();
		assertEquals(conn!= null, true);
		
	}
	/*
	@Test
	void testJason() throws ServletException, IOException, SQLException {
		UserController us = new UserController();
	
		JSONObject jo;
		jo=us.GetJasonUserList();
		assertFalse(jo== null);
	
		
	}
	*/
	@Test
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

		/*JSONAssert.assertEquals("{id:1}", jobj, false);
		JSONAssert.assertEquals("{name:Jason}", jobj, false);
		*/
	}
	/*
	@Test
	
	void testGetMaxId() throws Exception {
		System.out.println("test MAX id");{
	  
	     DaoUser user= DaoUser.getInstance();
	     int id = user.getMaxIndex();
	     
	     assertEquals(id - user.getMaxIndex()  , 0);
	     }
	}
	/*
	@Test
	void testUpdate() throws Exception {
		String date = "01/04/2001";
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());

		
		boolean rowup= false;
		DaoUser userTest= DaoUser.getInstance();
		User user = new User(3, "pako", "TestUpdate",data,creationtime,19,Status.C);
		
		rowup=userTest.update(user);
		assertEquals(true, rowup);
		
	}
	*/
	
	@Test
	void testSave()  throws Exception{
		boolean rowsave= false;
		String date = "01/04/2001";
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse("10/05/1980 17:30");
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		DaoUser userTest = DaoUser.getInstance();
		
		User user = new User(0, "Testperico", "Testcotorra",data,creationtime,29,Status.S);
		rowsave=userTest.save(user);
		assertEquals(true, rowsave);
	}
/*
	@Test
	void deleteTest() throws Exception {
	
		boolean rowdel= false;
		DaoUser userTest = DaoUser.getInstance();
		User user = new User(3);
		rowdel= userTest.delete(user);
		assertEquals(true, rowdel);
		
	}
	*/
}
