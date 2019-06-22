package org.concourplus.usersetup.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.model.usersetup.User;
import org.concourplus.usersetup.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;

	public Response<User> addUser(User user) {
		final Response<User> response = new Response<User>();
		if (!response.hasStatutError()) {
			user.setPassword(encryptPassword(user.getPassword(), user.getUsername()));
			final User userRef = userRepository.save(user);
			response.setModel(userRef);
		}
		return response;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public List<User> searchUsers(Request<User> request) {
		// TODO Auto-generated method stub
		return null;
	}


	public int searchUsersNumber(Request<User> request) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<User> getListSpecified(Request<User> request) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response<User> editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response<User> validateUser(Request<User> request) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response<User> getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer countUsers(Request<User> request) {
		// TODO Auto-generated method stub
		return null;
	}


	public Collection<User> getUsers(Request<Void> request) {
		// TODO Auto-generated method stub
		return null;
	}


	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		
	}


	public void updateUserToken(String token, Date tokenDate, Long id) {
		// TODO Auto-generated method stub
		
	}
	
	private String encryptPassword(final String password, final String salt) {
		return new ShaPasswordEncoder(256).encodePassword(password, salt);
	}

}
