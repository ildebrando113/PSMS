package it.enaip.stage.test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.enaip.stage.dao.DataSourceFactory;


class Junit_DB_TEST2 {

	
	
	@Test
	void testBbConn() {
		System.out.println("get Connection");
		Connection conn = DataSourceFactory.getConnection();
		assertEquals(conn!= null, true);
		
	}
	/*
	@Test
	
	void testGetMaxId() throws Exception {
		System.out.println("test MAX id");{
	  //  Connection conn = DataSourceFactory.getConnection();
	     DaoStuff dao= DaoStuff.getInstance();
	     int id = dao.getMaxIndex();
	     
	     assertEquals(id - dao.getMaxIndex()  , 0);
	     }
	}
	
	@Test
	void testUpdate() throws Exception {
		boolean rowup= false;
		DaoStuff dao= DaoStuff.getInstance();
		Stuff stuff = new Stuff(2,"Update", "test", 1, "test");
		
		rowup=dao.update(stuff);
		assertEquals(true, rowup);
		
	}
	
	
	@Test
	void testSave()  throws Exception{
		boolean rowsave= false;
		DaoStuff dao = DaoStuff.getInstance();
		Stuff stuff = new Stuff(0, "test-new", "test-desc",1, "test-loc");
		rowsave=dao.save(stuff);
		assertEquals(true, rowsave);
	}
	
	@Test
	void deleteTest() throws Exception {
	
		boolean rowdel= false;
		DaoStuff dao = DaoStuff.getInstance();
		Stuff stuff = new Stuff(3);
		rowdel= dao.delete(stuff);
		assertEquals(true, rowdel);
		
	}
	*/
}
