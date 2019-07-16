package org.concourplus.service.accesssetup;

import java.util.Date;

import org.concourplus.base.contract.Response;
import org.concourplus.dto.usersetup.UserDTO;

public interface AuthenticationService {

	public Response<UserDTO> login(String username, String password);
	public void updateUserToken(String token, Date tokenDate,  long userId);
	
}
