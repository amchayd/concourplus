package org.concourplus.business.auth.json;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.concourplus.dto.usersetup.ProfileDTO;
import org.concourplus.dto.usersetup.RoleDTO;

public class ProfileJson {
	private long id;
	private String code;
	private String label;
	private Set<RoleJson> roles;

	public ProfileJson(ProfileDTO profile) {
		this.code = profile.getCode();
		this.label = profile.getLabel();
		this.roles = RoleJson.objsToJson(profile.getRoles());
	}

	public static Set<ProfileJson> objsToJson(Set<ProfileDTO> profiles) {
		Set<ProfileJson> profilesJson = new HashSet<ProfileJson>();
		Iterator<ProfileDTO> it = profiles.iterator();
		while (it.hasNext()) {
			ProfileDTO current = (ProfileDTO) it.next();
			profilesJson.add(new ProfileJson(current));
		}
		return profilesJson;
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

	public Set<RoleJson> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleJson> roles) {
		this.roles = roles;
	}
	
	public ProfileDTO objToDto() {
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setCode(this.code);
		profileDTO.setLabel(this.label);
		
		Set<RoleDTO> rolesDto = new HashSet<RoleDTO>();
		for(RoleJson r : this.roles) {
			rolesDto.add(r.objToDto());
		}
		profileDTO.setRoles(rolesDto);
		return profileDTO;
	}

}
