package org.concourplus.business.auth.json;

import java.text.ParseException;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.concourplus.business.referential.json.AddressJson;
import org.concourplus.business.referential.json.SecretQuestionJson;
import org.concourplus.business.referential.json.UserStatusJson;
import org.concourplus.dto.referential.GenderDTO;
import org.concourplus.dto.usersetup.UserDTO;

public class UserJson {
	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthdate;
	private String phoneNumber;
	private String mail;
	private String username;
	private String password;
	private String token;
	private String tokenDate;
	private Boolean isConnected;
	private Boolean isAbsent;
	private String lastConnexion;
	private AddressJson address;
	private SecretQuestionJson secretQuestion;
	private String secretQuestionAnswer;
	private String creationDate;
	private String lastModificationDate;
	private UserStatusJson status;
	private String statusDescription;
	private Set<ProfileJson> profiles;

	public UserJson() {
	}

	public UserJson(UserDTO user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(String tokenDate) {
		this.tokenDate = tokenDate;
	}

	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	public Boolean getIsAbsent() {
		return isAbsent;
	}

	public void setIsAbsent(Boolean isAbsent) {
		this.isAbsent = isAbsent;
	}

	public String getLastConnexion() {
		return lastConnexion;
	}

	public void setLastConnexion(String lastConnexion) {
		this.lastConnexion = lastConnexion;
	}

	public AddressJson getAddress() {
		return address;
	}

	public void setAddress(AddressJson address) {
		this.address = address;
	}

	public SecretQuestionJson getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(SecretQuestionJson secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretQuestionAnswer() {
		return secretQuestionAnswer;
	}

	public void setSecretQuestionAnswer(String secretQuestionAnswer) {
		this.secretQuestionAnswer = secretQuestionAnswer;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public UserStatusJson getStatus() {
		return status;
	}

	public void setStatus(UserStatusJson status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Set<ProfileJson> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<ProfileJson> profiles) {
		this.profiles = profiles;
	}

	public UserDTO objToDto() throws ParseException {
		UserDTO user = new UserDTO();
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		if (this.gender.equals("MALE")) {
			user.setGender(GenderDTO.MALE);
		} else {
			user.setGender(GenderDTO.MALE);
		}
		user.setBirthdate(DateUtils.parseDate(this.birthdate, new String[] { "yyyy/MM/dd" }));
		user.setPhoneNumber(this.phoneNumber);
		user.setMail(this.mail);
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setSecretQuestion(this.secretQuestion.objToDto());
		user.setSecretQuestionAnswer(this.secretQuestionAnswer);
		return user;
	}

}
