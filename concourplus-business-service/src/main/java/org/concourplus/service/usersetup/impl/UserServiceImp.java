package org.concourplus.service.usersetup.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.base.util.ConstantBase;
import org.concourplus.base.util.SpecificationsHelper;
import org.concourplus.dao.referential.SecretQuestionRepository;
import org.concourplus.dao.usersetup.ProfileRepository;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.referential.SecretQuestion;
import org.concourplus.model.referential.UserStatus;
import org.concourplus.model.usersetup.Profile;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.mapper.EnumMapper;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("userService")
public class UserServiceImp implements UserService, UserSetUpConstants {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private SecretQuestionRepository secretQuestionRepository;

	@Autowired
	private MessageSource messageSourceUserSetup;

	@Autowired
	private transient MapperService mapperService;

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
	public Response<UserDTO> addUser(UserDTO user) {
		final Response<UserDTO> response = new Response<UserDTO>();
		user.setCreationDate(new Date());
		user.setLastModificationDate(new Date());

		this.validate(user, response);
		if (!response.hasStatutError()) {
			user.setPassword(bcryptPassword(user.getPassword()));
			final User userToSave = mapperService.map(user, User.class, EnumMapper.MAPPING_USER_REGISTER.toString());

			// NW (New User) : A first status assigned to user when he created an account
			UserStatus userStatus = userRepository.getStatusByCode("NW");
			if (userStatus != null) {
				userToSave.setStatus(userStatus);
			}

			// US (User) : A first Profile assigned to user when he created an account
			Profile profile = profileRepository.getProfileByCode("US");
			Set<Profile> profiles = new HashSet<Profile>();
			profiles.add(profile);
			userToSave.setProfiles(profiles);

			SecretQuestion secretQuestion = secretQuestionRepository
					.getSecretQuestionByCode(user.getSecretQuestion().getCode());
			if (secretQuestion != null) {
				userToSave.setSecretQuestion(secretQuestion);
			} else {
				response.setStatus(Response.STATUS_ERROR);
				response.clearMessage();
				response.addMessage("Error! Wrong Secret Question");

				return response;
			}

			final User userRef = userRepository.save(userToSave);
			final UserDTO userToReturn = mapperService.map(userRef, UserDTO.class,
					EnumMapper.MAPPING_USER_REGISTER.toString());
			response.setModel(userToReturn);
		}
		return response;
	}

	private void validate(final UserDTO user, final Response<UserDTO> response) {
		final boolean existsUserName = existsByUserName(user.getUsername());
		if (existsUserName) {
			response.addMessage(messageSourceUserSetup.getMessage(USERNAME_EXISTANT,
					new String[] { user.getUsername() }, ConstantBase.DEFAULT_MESSAGE, null));
		}

		if (response.hasMessages()) {
			response.setStatus(Response.STATUS_ERROR);
		}
	}

	private boolean existsByUserName(final String userName) {
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

	public Response<UserDTO> editUser(UserDTO user) {
		final Response<UserDTO> response = new Response<UserDTO>();
		User userTosave = mapperService.map(user, User.class, EnumMapper.MAPPING_USER_EDIT.toString());
		userTosave.setLastModificationDate(new Date());
		final User userRef = userRepository.save(userTosave);
		UserDTO userDto = mapperService.map(userRef, UserDTO.class, EnumMapper.MAPPING_BASIC_USER.toString());
		response.setModel(userDto);
		response.setStatus(Response.STATUS_SUCCES);
		return response;
	}

	public Response<UserDTO> validateUser(Request<UserDTO> request) {
		Response<UserDTO> response = new Response<UserDTO>();
		this.validate(request.getModel(), response);
		return response;
	}

	public Response<User> getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO getUserById(Long id) {
		UserDTO userDto = null;
		User userRef = userRepository.findOne(id);
		if (userRef != null) {
			userDto = mapperService.map(userRef, UserDTO.class, EnumMapper.MAPPING_BASIC_USER.toString());
		}
		return userDto;
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

	@Override
	public Collection<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		Collection<UserDTO> usersDto = new ArrayList<UserDTO>();
		if (users != null) {
			usersDto = mapperService.mapCollection(users, UserDTO.class, EnumMapper.MAPPING_BASIC_USER.toString());
		}

		return usersDto;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
	public UserDTO getUserByUserName(String username) {
		User user = userRepository.findByUsername(username);
		return mapperService.map(user, UserDTO.class, EnumMapper.MAPPING_USER_EDIT.toString());
	}

}
