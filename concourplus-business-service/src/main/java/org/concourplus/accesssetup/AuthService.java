package org.concourplus.accesssetup;

import java.util.Date;

import org.concourplus.base.contract.Response;
import org.concourplus.model.usersetup.User;

public interface AuthService {

	public Response<User> login(String username, String password);
	public void updateUserToken(String token, Date tokenDate,  long id);
	
}
