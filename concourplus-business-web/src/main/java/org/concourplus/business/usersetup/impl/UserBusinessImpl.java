package org.concourplus.business.usersetup.impl;

import org.concourplus.base.contract.Response;
import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.business.usersetup.UserBusiness;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.service.usersetup.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userBusiness")
public class UserBusinessImpl implements UserBusiness {

	@Autowired
	UserService userService;

	@Override
	public JsonResult addUser(UserJson user) {
		JsonResult result = new JsonResult();
		try {
			UserDTO userDto = user.objToDto();
			Response<UserDTO> responseRef = userService.addUser(userDto);
			if (responseRef.hasStatutError()) {
				result.setStatus(responseRef.getStatus());
				result.setMessages(responseRef.getMessages());
			} else {
				result.setStatus(JsonStatus.SUCCESS_STATUS.toString());
				result.getMessages().add("Success! Page saved successfully");
				result.getData().put("user", new UserJson(responseRef.getModel()));
			}

			return result;
		} catch (Exception e) {
			throw e;
		}
	}

}
