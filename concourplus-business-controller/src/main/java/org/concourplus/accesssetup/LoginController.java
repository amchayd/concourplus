package org.concourplus.accesssetup;

import org.concourplus.helpers.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/auth")
public class LoginController {

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JsonResult login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		return null;
	}
}
