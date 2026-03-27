package tech.lander.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {

	@Test
	public void testApp(){
		HomeController hc = new HomeController();
		String result = hc.home();
		assertEquals("All your base are belong to us.", result);
	}

}