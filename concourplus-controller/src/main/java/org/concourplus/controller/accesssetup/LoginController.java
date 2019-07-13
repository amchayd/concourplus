package org.concourplus.controller.accesssetup;
import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.helpers.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		
			return authenticationBusiness.login(username, password);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String logout() {
		
			return "LOGOUT";
	}
}
