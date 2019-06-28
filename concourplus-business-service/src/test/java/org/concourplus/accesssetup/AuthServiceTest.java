package org.concourplus.accesssetup;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthServiceTest {

	private static AuthService authService;
	private static ClassPathXmlApplicationContext context;
	
	   
    @BeforeClass
    public static void initService() throws Exception {
     context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
     authService = context.getBean("authService", AuthService.class);
    }
    
    @Test
    public void testLogin() {
    	
    }
}
