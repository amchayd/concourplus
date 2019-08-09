package org.concourplus.dto.usersetup;

import java.util.Set;

public class ProfileDTO {
	private long id;
	private String code;
	private String label;
	private Set<RoleDTO> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return " {code:" + code + ", label:" + label + ", roles:[" + roles + "]}";
	}

}
