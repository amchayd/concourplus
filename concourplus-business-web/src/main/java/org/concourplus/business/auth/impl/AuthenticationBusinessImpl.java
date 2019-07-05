package org.concourplus.business.auth.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;

import org.concourplus.base.contract.Response;
import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.accesssetup.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authenticationBusiness")
public class AuthenticationBusinessImpl implements AuthenticationBusiness{

	@Autowired
	private AuthenticationService authenticationService; 

	private SecureRandom random = new SecureRandom();
	
	@Override
	public JsonResult login(String username, String password) {
		try {
		JsonResult result = new JsonResult();
		List<String> messages = new ArrayList<>();
		Response<User> response = authenticationService.login(username, password);
		if (response != null && response.hasStatutSucces()) {
			 User user = response.getModel();
			if(user.getToken() == null) {
				String token = getRandomToken();
				user.setToken(token);
				user.setTokenDate(Calendar.getInstance().getTime());

				response.setModel(user);			
				authenticationService.updateUserToken(user.getToken(), user.getTokenDate(), user.getId());
			}
			
			response.setStatus(Response.STATUS_SUCCES);
			messages.add("Login Success!");

			Map<String, Object> userMap = new HashMap<>();
			userMap.put("id", user.getId());
			userMap.put("firstName", user.getFirstName());
			userMap.put("lastName", user.getLastName());
			userMap.put("token", user.getToken());
			
			result.setStatus(JsonStatus.SUCCESS_STATUS.toString());
			result.setMessages(messages);
			result.getData().put("user", userMap);
			
		}else {
			result.setStatus(JsonStatus.ERROR_STATUS.toString());
			result.setMessages(messages);
			result.getData().put("user", null);
		}
		
		return result;
		
		}catch (FacesException e) {
			throw new FacesException(e);
		}
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


	private String getRandomToken() {
		return new BigInteger(130, random).toString(32);
	}
}
