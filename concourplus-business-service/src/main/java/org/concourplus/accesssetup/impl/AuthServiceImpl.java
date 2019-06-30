package org.concourplus.accesssetup.impl;

import java.util.Date;

import org.concourplus.accesssetup.AuthService;
import org.concourplus.base.contract.Response;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.model.usersetup.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService{

	private static final String INVALIDE_LOGIN_PASS = "AUTH-01";
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public Response<User> login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserToken(String token, Date tokenDate, long id) {
		// TODO Auto-generated method stub
		
	}

}
