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
	public JsonResult addUser(UserJson user) throws Exception {
		JsonResult result = new JsonResult();
		boolean errorExist = false;
		if (!checkField(user.getFirstName())) {
			result.getMessages().add("Error! the firstName field is required");
			errorExist = true;
		}
		if (!checkField(user.getLastName())) {
			result.getMessages().add("Error! the lastName field is required");
			errorExist = true;
		}
		if (!checkField(user.getGender())) {
			result.getMessages().add("Error! the gender field is required");
			errorExist = true;
		}
		if (!checkField(user.getBirthdate())) {
			result.getMessages().add("Error! the birthday field is required");
			errorExist = true;
		}
		if (!checkField(user.getPhoneNumber())) {
			result.getMessages().add("Error! the phoneNumber field is required");
			errorExist = true;
		}
		if (!checkField(user.getMail())) {
			result.getMessages().add("Error! the eMail field is required");
			errorExist = true;
		}
		if (!checkField(user.getUsername())) {
			result.getMessages().add("Error! the userName field is required");
			errorExist = true;
		}
		if (!checkField(user.getPassword())) {
			result.getMessages().add("Error! the password field is required");
			errorExist = true;
		}
		if (!checkField(user.getSecretQuestion())) {
			result.getMessages().add("Error! the secretQuestion field is required");
			errorExist = true;
		}
		if (!checkField(user.getSecretQuestionAnswer())) {
			result.getMessages().add("Error! the secretQuestionAnswer field is required");
			errorExist = true;
		}
		if (errorExist) {
			result.setStatus(JsonStatus.ERROR_STATUS.toString());
			return result;
		}
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

	public boolean checkField(Object field) {
		if (field == null) {
			return false;
		} else {
			if (field instanceof String) {
				if (field.toString().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}
