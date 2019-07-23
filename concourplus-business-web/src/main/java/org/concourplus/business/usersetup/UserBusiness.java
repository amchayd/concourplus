package org.concourplus.business.usersetup;

import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;

public interface UserBusiness {
	public JsonResult addUser(UserJson user) throws Exception;
}
