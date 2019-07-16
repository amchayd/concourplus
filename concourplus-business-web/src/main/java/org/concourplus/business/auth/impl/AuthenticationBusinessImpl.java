package org.concourplus.business.auth.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.business.auth.AuthenticationBusiness;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.accesssetup.AuthenticationService;
import org.concourplus.service.usersetup.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authenticationBusiness")
public class AuthenticationBusinessImpl implements AuthenticationBusiness{
	
	private static final String MAPPING_USER_AUTHENTICATION = "MAPPING_USER_AUTHENTICATION";
	
	@Autowired
	private AuthenticationService authenticationService; 

	@Autowired
	private UserService userService;
	
	private SecureRandom random = new SecureRandom();
	
	@Override
	public JsonResult login(String username, String password) {
		try {
		JsonResult result = new JsonResult();
		List<String> messages = new ArrayList<>();
		Response<UserDTO> response = authenticationService.login(username, password);
		if (response != null && response.hasStatutSucces()) {
			 UserDTO user = response.getModel();
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
			userMap.put("username", user.getUsername());
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
		
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void logout(String token) {
		try {
			Request<UserDTO> request = new Request<UserDTO>();
			              request.addVariable("tokenEq", token);
			              request.setConverterId(MAPPING_USER_AUTHENTICATION);
			              List<UserDTO> list = (List<UserDTO>) userService.getUsers(request);
            if (list.isEmpty()) {
  				return;
            }else {
  				UserDTO user = list.get(0);
  				userService.updateUserToken(null, null, user.getId());
  				return;
			  			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public JsonResult check(String token) {
		try {
			JsonResult result = new JsonResult();
			if (token == null) {
				result.setStatus(JsonStatus.ERROR_STATUS.toString());
				result.getMessages().add("Error! Token is null");
			}else {
				Request<UserDTO> request = new Request<UserDTO>();
							  request.addVariable("tokenEq", token);
							  request.setConverterId(MAPPING_USER_AUTHENTICATION);
				  List<UserDTO> list =  (List<UserDTO>) userService.getUsers(request);
		            if (list.isEmpty()) {
		            	result.setStatus(JsonStatus.ERROR_STATUS.toString());
						result.getMessages().add("Error! Token not found");
		            } else {
		            	UserDTO user = list.get(0);
		            	result.setStatus(JsonStatus.SUCCESS_STATUS.toString());
						result.getMessages().add("Success! User found");
						result.getData().put("user", user);
		            }							  			
			}
			return result;
		} catch (Exception e) {
			throw e;
		}
	}


	private String getRandomToken() {
		return new BigInteger(130, random).toString(32);
	}
}
