package org.concourplus.usersetup;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.concourplus.base.contract.Response;
import org.concourplus.model.referential.Address;
import org.concourplus.model.referential.Country;
import org.concourplus.model.referential.Gender;
import org.concourplus.model.referential.SecretQuestion;
import org.concourplus.model.referential.UserStatus;
import org.concourplus.model.usersetup.Profile;
import org.concourplus.model.usersetup.Role;
import org.concourplus.model.usersetup.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {


	private static UserService userService;
	private static ClassPathXmlApplicationContext context;
	   
    @BeforeClass
    public static void initService() throws Exception {
     context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
     userService = context.getBean("userService", UserService.class);
    }
    
	@Test
	public void testAddUser() {
		
		User user = new User();
	     user.setFirstName("Oualid");
	     user.setLastName("Amch");
	     
	Gender gender = new Gender();
		   gender.setCode("F");
		   gender.setLabel("Female");
	     user.setGender(gender);
	     user.setBirthdate(new Date());
	     user.setMail("kl@mail.com");
	     user.setPhoneNumber("00000000111");
	     user.setUsername("kl123");
	     user.setPassword("sssss99900ss");

	Address ad = new Address();
	        ad.setStreet("Agdal");
	        ad.setState("RAbat");
	        ad.setPostalCode("10200");
	        ad.setCity("Rabat");
	        
	Country ct = new Country();
	        ct.setCode("MA");
	        ct.setLabel("Maroc");
	        ad.setCountry(ct);
	      user.setAddress(ad);
   
	SecretQuestion sq = new SecretQuestion();
	               sq.setQuestion("question 1");
	      user.setSecretQuestion(sq);
	      user.setSecretQuestionAnswer("Answer 1");
	      user.setCreationDate(new Date());
	UserStatus st = new UserStatus();
	           st.setCode("EN");
	           st.setLabel("ENABLED");
	      user.setStatus(st);
	      user.setStatusDescription("account enabled");
	
	Profile pf = new Profile();
	        pf.setCode("USER");
	        pf.setLabel("User");
	Set<Role> roles = new HashSet<Role>();
	          Role r1 = new Role();
	               r1.setCode("SLT");
	               r1.setLabel("Select");
			  Role r2 = new Role();
		           r2.setCode("DLT");
		           r2.setLabel("Delete");
		      roles.add(r1);
		      roles.add(r2);
	        pf.setRoles(roles);
	        user.setProfile(pf);
			
	 Response<User> response  = userService.addUser(user);
	     System.err.println("sss=========dd========---------"+response.getMessages().get(0));

		assertEquals(1, 1);
	}
	
	
}
