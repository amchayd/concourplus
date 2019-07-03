package org.concourplus.service.usersetup;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.concourplus.base.contract.Request;
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
	public void testStep1AddUser() {
		User user = createUser("kl123", "pass123");
		Response<User> response = userService.addUser(user);
		assertEquals(response.getStatus(), Response.STATUS_ERROR);

	}

	@Test
	public void testStep2UpdateUser() {
		User userRef = userService.getUserById((long) 1);
		userRef.setLastName("Abou Amal");
		Response<User> response = userService.editUser(userRef);
		assertEquals(response.getStatus(), Response.STATUS_SUCCES);
	}

	@Test
	public void testStep3getUsersBySpecification() {
		Request<User> request = new Request<>();
		request.addVariable("loginIsEqual", "kl123");

		List<User> listSpecification = (List<User>) userService.getUsers(request);
		assertEquals("Many user have the same username", 1, listSpecification.size());
	}

	private User createUser(String userName, String password) {
		User user = new User();
		user.setFirstName("Oualid");
		user.setLastName("Amch");

		user.setGender(Gender.MALE);
		user.setBirthdate(new Date());
		user.setMail("kl@mail.com");
		user.setPhoneNumber("00000000111");
		user.setUsername(userName);
		user.setPassword(password);

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
		Set<Profile> pfs = new HashSet<Profile>();
		pfs.add(pf);
		user.setProfiles(pfs);

		return user;
	}

}
