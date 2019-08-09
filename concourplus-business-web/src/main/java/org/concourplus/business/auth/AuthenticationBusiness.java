package org.concourplus.business.auth;

import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;

public interface AuthenticationBusiness {

	public JsonResult login(String username, String password);

	public void logout(String token);

	public JsonResult check(String token);
	
	public String generateToken(UserJson user);
}
