package org.concourplus.business.auth;

import org.concourplus.business.helpers.JsonResult;

public interface AuthenticationBusiness {

	public JsonResult login(String username, String password);

	public void logout(String token);

	public JsonResult check(String token);
}
