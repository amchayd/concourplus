package org.concourplus.service.usersetup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.dto.referential.GenderDTO;
import org.concourplus.dto.referential.SecretQuestionDTO;
import org.concourplus.dto.referential.UserStatusDTO;
import org.concourplus.dto.usersetup.ProfileDTO;
import org.concourplus.dto.usersetup.RoleDTO;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	private static UserService userService;
	private static ProfileService profileService;
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void initService() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		userService = context.getBean("userService", UserService.class);
		profileService = context.getBean("profileService", ProfileService.class);
	}

	@Test
	public void testStep1FindAll() {
		Collection<UserDTO> users = userService.findAll();
		assertNotEquals("List Users is empty", users.size(), 0);
	}

	@Ignore
	public void testStep1AddUser() {
		UserDTO user = createUser("kl1234", "pass123");
		Response<UserDTO> response = userService.addUser(user);
		assertEquals("Error :" + response.getMessages().get(0), Response.STATUS_ERROR, response.getStatus());

	}

	@Test
	public void testStep2EditUser() {
		UserDTO userRef = userService.getUserByUserName("kl1234");
		assertNotNull("User doesn't existe", userRef);
		ProfileDTO newProfile = profileService.getProfileByCode("AD");
		userRef.getProfiles().add(newProfile);
		Response<UserDTO> response = userService.editUser(userRef);
		assertEquals(response.getStatus(), Response.STATUS_SUCCES);

	}

	@Test
	public void testStep3getUsersBySpecification() {
		Request<UserDTO> request = new Request<>();
		request.addVariable("userNameIsEqual", "kl1234");

		List<UserDTO> listSpecification = (List<UserDTO>) userService.getUsers(request);
		assertEquals("Many user have the same username", 1, listSpecification.size());
	}

	@Test
	public void testStep4GetUserById() {
		long id = 11;
		UserDTO user = userService.getUserById(id);
		assertNotNull("User is null", user);
	}

	@Test
	public void testStep5GetUserByUsername() {
		UserDTO user = userService.getUserByUserName("kl1234");
		assertNotNull("User is null", user);
	}

	private UserDTO createUser(String userName, String password) {
		UserDTO user = new UserDTO();
		user.setFirstName("Oualid");
		user.setLastName("Amch");

		user.setGender(GenderDTO.MALE);
		user.setBirthdate(new Date());
		user.setMail("kl@mail.com");
		user.setPhoneNumber("00000000111");
		user.setUsername(userName);
		user.setPassword(password);

		SecretQuestionDTO sq = new SecretQuestionDTO();
		sq.setCode("Q1");
		sq.setQuestion("question 1");
		user.setSecretQuestion(sq);
		user.setSecretQuestionAnswer("Answer 1");

		return user;
	}

}
