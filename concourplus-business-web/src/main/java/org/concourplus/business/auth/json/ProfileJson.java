package org.concourplus.business.auth.json;

import java.util.Set;

public class ProfileJson {
	private long id;
	private String code;
	private String label;
	private Set<RoleJson> roles;

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

	public Set<RoleJson> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleJson> roles) {
		this.roles = roles;
	}

}
