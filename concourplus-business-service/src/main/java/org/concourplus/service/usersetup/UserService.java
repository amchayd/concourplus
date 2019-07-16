package org.concourplus.service.usersetup;

import java.util.Collection;
import java.util.Date;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;


public interface UserService {

	Response<User> addUser(User user);

	Response<User> editUser(User user);

	Response<User> validateUser(Request<User> request);

	Response<User> getUser(User user);

	User getUserById(Long id);

	Collection<UserDTO> getUsers(Request<UserDTO> request);

	void saveOrUpdate(User user);

	void updateUserToken(String token, Date tokenDate, Long id);

}
