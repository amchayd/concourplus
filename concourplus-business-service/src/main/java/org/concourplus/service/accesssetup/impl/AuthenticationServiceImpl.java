package org.concourplus.service.accesssetup.impl;

import java.util.Date;

import org.concourplus.base.contract.Response;
import org.concourplus.base.util.ConstantBase;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.accesssetup.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authenticationService")
@Transactional(readOnly = false)
public class AuthenticationServiceImpl implements AuthenticationService{

	private static final String INVALIDE_LOGIN_PASS = "AUTH-01";
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public Response<User> login(String username, String password) {
		Response<User> response = new Response<User>();
		try {
			Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authResult = authenticationManager.authenticate(authRequest);

			SecurityContextHolder.getContext().setAuthentication(authResult);
			User user = userRepository.findByUsername(username);

			response.setModel(user);
			response.setStatus(Response.STATUS_SUCCES);

		} catch (AuthenticationException aex) {
			response.setStatus(Response.STATUS_ERROR);
			response.addMessage(messageSource.getMessage(INVALIDE_LOGIN_PASS, null, ConstantBase.DEFAULT_MESSAGE, null));
		}
		return response;
	}

	@Override
	public void updateUserToken(String token, Date tokenDate, long userId) {
		userRepository.updateUserToken(token, tokenDate, userId);		
	}

}
