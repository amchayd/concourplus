package org.concourplus.service.accesssetup;

import static org.junit.Assert.assertEquals;

import org.concourplus.base.contract.Response;
import org.concourplus.dto.usersetup.UserDTO;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthServiceTest {

	private static AuthenticationService authenticationService;
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void initService() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		authenticationService = context.getBean("authenticationService", AuthenticationService.class);
	}

	@Ignore
	public void testLogin() {
		Response<UserDTO> response = authenticationService.login("0004", "toto");

		assertEquals("Username or Password are invalid :", Response.STATUS_SUCCES, response.getStatus());
	}

}
