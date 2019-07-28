package org.concourplus.business.usersetup.impl;

import org.concourplus.base.contract.Response;
import org.concourplus.base.util.ConstantBase;
import org.concourplus.business.auth.json.UserJson;
import org.concourplus.business.helpers.JsonResult;
import org.concourplus.business.helpers.JsonStatus;
import org.concourplus.business.usersetup.UserBusiness;
import org.concourplus.dto.usersetup.UserDTO;
import org.concourplus.service.usersetup.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("userBusiness")
public class UserBusinessImpl implements UserBusiness {
	private static final String FIELD_REQUIRED = "FIELD-01";
	@Autowired
	UserService userService;

	@Autowired
	private MessageSource messageSourceCommon;

	@Override
	public JsonResult addUser(UserJson user) throws Exception {
		JsonResult result = new JsonResult();
		boolean errorExist = false;
		if (!checkField(user.getFirstName())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "firstName" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getLastName())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "lastName" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getGender())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "gender" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getBirthdate())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "birthdate" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getPhoneNumber())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "phoneNumber" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getMail())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "eMail" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getUsername())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "userName" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getPassword())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "password" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getSecretQuestion())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED, new String[] { "secretQuestion" },
					ConstantBase.DEFAULT_MESSAGE, null));
			errorExist = true;
		}
		if (!checkField(user.getSecretQuestionAnswer())) {
			result.getMessages().add(messageSourceCommon.getMessage(FIELD_REQUIRED,
					new String[] { "secretQuestionAnswer" }, ConstantBase.DEFAULT_MESSAGE, null));
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
