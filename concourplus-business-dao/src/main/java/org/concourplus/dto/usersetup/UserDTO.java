package org.concourplus.dto.usersetup;

import java.util.Date;
import java.util.Set;

import org.concourplus.dto.referential.AddressDTO;
import org.concourplus.dto.referential.GenderDTO;
import org.concourplus.dto.referential.SecretQuestionDTO;
import org.concourplus.dto.referential.UserStatusDTO;

public class UserDTO {

	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private GenderDTO gender;
    
	private Date birthdate;
	
	private String phoneNumber;
	
	private String mail;
	
	private String username;
	
	private String password;
	
	private String token;
	
	private Date tokenDate;
	
	private Boolean isConnected;

	private Boolean isAbsent;

	private Date lastConnexion;
	
	private AddressDTO address;
	
	private SecretQuestionDTO secretQuestion;
	
	private String secretQuestionAnswer;
	
	private Date creationDate;
	
	private Date lastModificationDate;
	
	private UserStatusDTO status;
	
	private String statusDescription; 
	
	private Set<ProfileDTO> profiles;

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

	public GenderDTO getGender() {
		return gender;
	}

	public void setGender(GenderDTO gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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

	public Date getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(Date tokenDate) {
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

	public Date getLastConnexion() {
		return lastConnexion;
	}

	public void setLastConnexion(Date lastConnexion) {
		this.lastConnexion = lastConnexion;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public SecretQuestionDTO getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(SecretQuestionDTO secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretQuestionAnswer() {
		return secretQuestionAnswer;
	}

	public void setSecretQuestionAnswer(String secretQuestionAnswer) {
		this.secretQuestionAnswer = secretQuestionAnswer;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public UserStatusDTO getStatus() {
		return status;
	}

	public void setStatus(UserStatusDTO status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Set<ProfileDTO> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<ProfileDTO> profiles) {
		this.profiles = profiles;
	}

	
}
