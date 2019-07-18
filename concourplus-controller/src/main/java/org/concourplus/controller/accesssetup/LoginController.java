package org.concourplus.controller.accesssetup;

import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.business.usersetup.UserBusiness;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.service.usersetup.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/auth")
@EnableWebMvc
public class LoginController {

	@Autowired
	private AuthenticationBusiness authenticationBusiness;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserBusiness userBusiness;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		return authenticationBusiness.login(username, password);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult logout(@RequestParam(value = "token", required = true) String token) {

		authenticationBusiness.logout(token);

		JsonResult result = new JsonResult();
		result.setStatus(JsonStatus.SUCCESS_STATUS.toString());
		result.getMessages().add("Logged off successfully!");

		return result;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult check(@RequestParam(value = "token", required = true) String token) {

		JsonResult result = authenticationBusiness.check(token);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult register(@RequestBody UserJson user) {
		return userBusiness.addUser(user);
	}
}
