package org.concourplus.business.auth.json;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.concourplus.dto.usersetup.RoleDTO;

public class RoleJson {
	private long id;
	private String code;
	private String label;

	public RoleJson(RoleDTO role) {
		this.code = role.getCode();
		this.label = role.getLabel();
	}

	public static Set<RoleJson> objsToJson(Set<RoleDTO> roles) {
		Set<RoleJson> rolesJson = new HashSet<RoleJson>();
		Iterator<RoleDTO> it = roles.iterator();
		while (it.hasNext()) {
			RoleDTO current = (RoleDTO) it.next();
			rolesJson.add(new RoleJson(current));
		}
		return rolesJson;
	}

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

	public RoleDTO objToDto() {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setCode(this.code);
		roleDTO.setLabel(this.label);
		return roleDTO;
	}

}
