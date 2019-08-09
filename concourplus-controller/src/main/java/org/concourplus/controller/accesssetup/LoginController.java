package org.concourplus.controller.accesssetup;

import javax.servlet.http.HttpServletResponse;

import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.business.usersetup.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
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
	private UserBusiness userBusiness;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult login(@RequestBody UserJson user, HttpServletResponse response) {

		JsonResult jsonResult = authenticationBusiness.login(user.getUsername(), user.getPassword());
		if (jsonResult.getStatus().equals(JsonStatus.SUCCESS_STATUS.toString())) {
			response.setHeader("Access-Control-Expose-Headers", "Token");
			UserJson userRef = (UserJson) jsonResult.getData().get("user");
			response.setHeader("Token", authenticationBusiness.generateToken(userRef));
		}
		return jsonResult;
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
	public JsonResult register(@RequestBody UserJson user) throws Exception {
		return userBusiness.addUser(user);
	}
}
