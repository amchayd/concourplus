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


@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationBusiness authenticationBusiness; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult login1() {
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public JsonResult login() {
		System.out.println("===============GET==================");
		System.out.println("========"+authenticationBusiness.login("", "")+"=======");

		return null;
	}
}
