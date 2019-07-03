package org.concourplus.business.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.concourplus.base.contract.Response;
import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.accesssetup.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authenticationBusiness")
public class AuthenticationBusinessImpl implements AuthenticationBusiness{

	@Autowired
	private AuthenticationService authenticationService; 
	
	@Override
	public JsonResult login(String username, String password) {
		JsonResult result = new JsonResult();
		List<String> messages = new ArrayList<>();
		Response<User> response = authenticationService.login(username, password);
		return null;
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonResult check(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
