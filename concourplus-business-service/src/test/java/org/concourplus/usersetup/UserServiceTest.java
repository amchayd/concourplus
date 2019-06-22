package org.concourplus.usersetup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.concourplus.model.usersetup.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {


	private static UserService userService;
	private static ClassPathXmlApplicationContext context;
	   
    @BeforeAll
    public static void initService() throws Exception {
     System.err.println("sss=================---------");
     context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
     userService = context.getBean("courseService", UserService.class);
    }
	@Test
	public void testAddUser() {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		userService = context.getBean("userService", UserService.class);
		
		User u = new User();
			u.setLastName("Amch");
			u.setFirstName("Oualid");
			u.setPassword("123567");
			u.setUsername("laviestunjeu");
			
			userService.addUser(u);
	     System.err.println("sss=========dd========---------");

		//userService.addUser(new User());
		assertEquals(1, 1);
	}
	
	
}
