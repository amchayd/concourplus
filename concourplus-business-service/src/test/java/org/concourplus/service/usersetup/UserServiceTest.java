package org.concourplus.service.usersetup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void initService() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		userService = context.getBean("userService", UserService.class);
	}

	@Ignore
	public void testStep1AddUser() {
		UserDTO user = createUser("kl1234", "pass123");
		Response<UserDTO> response = userService.addUser(user);
		assertTrue("Has Status Error :",response.hasStatutError());
		assertEquals("Error :"+response.getMessages().get(0) ,Response.STATUS_ERROR, response.getStatus());

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
		Request<UserDTO> request = new Request<>();
		request.addVariable("userNameIsEqual", "kl123");

		List<UserDTO> listSpecification = (List<UserDTO>) userService.getUsers(request);
		assertEquals("Many user have the same username", 1, listSpecification.size());
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
		sq.setQuestion("question 1");
		user.setSecretQuestion(sq);
		user.setSecretQuestionAnswer("Answer 1");
		
		UserStatusDTO st = new UserStatusDTO();
		st.setCode("EN");
		st.setLabel("ENABLED");
		user.setStatus(st);
		user.setStatusDescription("account enabled");

		ProfileDTO pf = new ProfileDTO();
		pf.setCode("USER");
		pf.setLabel("User");
		Set<RoleDTO> roles = new HashSet<RoleDTO>();
		RoleDTO r1 = new RoleDTO();
		r1.setCode("SLT");
		r1.setLabel("Select");
		RoleDTO r2 = new RoleDTO();
		r2.setCode("DLT");
		r2.setLabel("Delete");
		roles.add(r1);
		roles.add(r2);
		pf.setRoles(roles);
		Set<ProfileDTO> pfs = new HashSet<ProfileDTO>();
		pfs.add(pf);
		user.setProfiles(pfs);

		return user;
	}

}
