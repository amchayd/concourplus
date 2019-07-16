package org.concourplus.service.accesssetup.impl;

import java.util.Date;

import org.concourplus.base.contract.Response;
import org.concourplus.base.util.ConstantBase;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.accesssetup.AuthenticationService;
import org.concourplus.service.mapper.EnumMapper;
import org.concourplus.service.mapper.MapperService;
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
	
	@Autowired
	private transient MapperService mapperService;
	
	@Override
	public Response<UserDTO> login(String username, String password) {
		Response<UserDTO> response = new Response<UserDTO>();
		try {
			Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authResult = authenticationManager.authenticate(authRequest);

			SecurityContextHolder.getContext().setAuthentication(authResult);
			User user = userRepository.findByUsername(username);
			
			if (user != null && user.getId() != 0) {
				final UserDTO userDto = mapperService.map(user, UserDTO.class, EnumMapper.MAPPING_USER_AUTHENTICATION.toString());
				mapperService.map(user, userDto, EnumMapper.MAPPING_USER_AUTHENTICATION.toString());
				response.setModel(userDto);
			}
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
