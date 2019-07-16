package org.concourplus.service.usersetup.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.base.util.ConstantBase;
import org.concourplus.base.util.SpecificationsHelper;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.mapper.MapperService;
import org.concourplus.service.usersetup.UserService;
import org.concourplus.service.usersetup.UserSetUpConstants;
import org.concourplus.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("userService")
public class UserServiceImp implements UserService, UserSetUpConstants {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private transient MapperService mapperService;

	public Response<User> addUser(User user) {
		final Response<User> response = new Response<User>();
		this.validate(user, response);
		if (!response.hasStatutError()) {
			user.setPassword(bcryptPassword(user.getPassword()));
			final User userRef = userRepository.save(user);
			response.setModel(userRef);
		}
		return response;
	}

	private void validate(final User user, final Response<User> response) {
		if (StringUtils.isEmpty(user.getUsername())) {
			/**
			 * register message non null login
			 */
			response.addMessage(messageSource.getMessage(INVALIDE_USERNAME, null, ConstantBase.DEFAULT_MESSAGE, null));
		}
		final boolean existsUserName = existsByUserName(user.getUsername(), user.getId());
		if (existsUserName) {
			response.addMessage(messageSource.getMessage(USERNAME_EXISTANT, new String[] { user.getUsername() },
					ConstantBase.DEFAULT_MESSAGE, null));
		}

		if (response.hasMessages()) {
			response.setStatus(Response.STATUS_ERROR);
		}
	}

	private boolean existsByUserName(final String userName, final Long id) {
		if (StringUtils.isEmpty(userName)) {
			return false;
		}
		final List<Specification<User>> specificationList = new ArrayList<Specification<User>>(2);
		specificationList.add(UserSpecification.userNameIsEqual(userName));

		final Specifications<User> specifications = SpecificationsHelper.buildWhereClause(specificationList);
		return userRepository.count(specifications) > 0;
	}

	// Encrypt using 256
	@SuppressWarnings("unused")
	private String encryptPassword(final String password, final String salt) {
		return new ShaPasswordEncoder(256).encodePassword(password, salt);
	}

	private String bcryptPassword(final String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);

	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Response<User> editUser(User user) {
		final Response<User> response = new Response<User>();
		final User userRef = userRepository.save(user);
		response.setModel(userRef);
		response.setStatus(Response.STATUS_SUCCES);
		return response;
	}

	public Response<User> validateUser(Request<User> request) {
		Response<User> response = new Response<User>();
		this.validate(request.getModel(), response);
		return response;
	}

	public Response<User> getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}
	@Transactional
	public Collection<UserDTO> getUsers(Request<UserDTO> request) {
		final Request<User> requestRef = SpecificationsHelper.convertRequest(request);
		final List<Specification<User>> specificationList = SpecificationsHelper
				.chooseSpecifications(UserSpecification.class, requestRef);
		final Specifications<User> specifications = SpecificationsHelper.buildWhereClause(specificationList);
		final Pageable page = SpecificationsHelper.buildPage(requestRef);
		final Page<User> resultat = userRepository.findAll(Specifications.where(specifications), page);
		Collection<User> users = resultat.getContent();
		return mapperService.mapCollection(users, UserDTO.class, request.getConverterId());
	}

	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub

	}

	public void updateUserToken(String token, Date tokenDate, Long id) {
		userRepository.updateUserToken(token, tokenDate, id);
	}
}
