package org.concourplus.service.accesssetup;

import static org.junit.Assert.assertEquals;

import org.concourplus.base.contract.Response;
import org.concourplus.model.usersetup.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthServiceTest {

	private static AuthenticationService authenticationService;
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void initService() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		authenticationService = context.getBean("authenticationService", AuthenticationService.class);
	}

	@Test
	public void testLogin() {
		Response<User> response = authenticationService.login("kl123", "pass123");

		assertEquals("Username or Password are invalid :", Response.STATUS_SUCCES, response.getStatus());
	}

}
