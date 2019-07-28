package org.concourplus.service.usersetup;

import java.util.Collection;
import java.util.Date;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.model.usersetup.User;

public interface UserService {

	public Response<UserDTO> addUser(UserDTO user);

	public Response<UserDTO> editUser(UserDTO user);

	public Response<UserDTO> validateUser(Request<UserDTO> request);

	public Response<User> getUser(User user);

	public UserDTO getUserById(Long id);

	public UserDTO getUserByUserName(String username);

	public Collection<UserDTO> getUsers(Request<UserDTO> request);

	public void saveOrUpdate(User user);

	public void updateUserToken(String token, Date tokenDate, Long id);

	public Collection<UserDTO> findAll();

}
