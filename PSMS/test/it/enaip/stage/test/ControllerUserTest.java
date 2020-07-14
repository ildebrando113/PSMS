package it.enaip.stage.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static net.sourceforge.jwebunit.junit.WebTestCase;
public class ControllerUserTest {


    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/test");
    }

    @Test
    public void testLogin() {
        beginAt("/home");
        clickLink("login");
        assertTitleEquals("Login");
        setTextField("username", "test");
        setTextField("password", "test123");
        submit();
        assertTitleEquals("Welcome, test!");
    }
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
