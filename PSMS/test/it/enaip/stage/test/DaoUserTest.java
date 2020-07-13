package it.enaip.stage.test;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import it.enaip.stage.dao.DaoUser;
import it.enaip.stage.dao.DataSourceFactory;
import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;

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
	void canSaveNewUser() throws ParseException, SQLException {
		boolean expected = daoUser.save(user);
		assertEquals(true, expected);		
	}
	
	@Test
	@Order(2)
	void canUpdateUser() throws ParseException, SQLException {
		user.setName("test_name_modified");
		user.setId(daoUser.getMaxIndex());
        boolean expected = daoUser.update(user);
		assertEquals(true, expected);		
	}
	
	@Test
	@Order(3)
	void canDeleteUser() throws ParseException, SQLException {
        boolean expected = daoUser.delete(user);
		assertEquals(true, expected);		
	}


}
