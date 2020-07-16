package it.enaip.stage.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


// OrderAnnotation class

@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {

	@Before
	public void setUp() throws Exception {
//		setBaseUrl()
	}

	@Test
	@Order(1)
	void testShowNewForm() {
		fail("Not yet implemented");
	}

}
