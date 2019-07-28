package org.concourplus.service.usersetup;

import static org.junit.Assert.assertNotNull;

import org.concourplus.dto.usersetup.ProfileDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProfileServiceTest {
	private static ProfileService profileService;
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void initService() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		profileService = context.getBean("profileService", ProfileService.class);
	}

	@Test
	public void testGetProfileByCode() {
		ProfileDTO profile = profileService.getProfileByCode("US");
		assertNotNull("Profile doesn't exist :", profile);
		assertNotNull("Profile has not roles :", profile.getRoles());
	}
}
