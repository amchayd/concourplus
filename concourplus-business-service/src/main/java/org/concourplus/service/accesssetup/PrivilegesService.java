package org.concourplus.service.accesssetup;

import java.util.List;

import org.concourplus.base.contract.Request;
import org.concourplus.base.contract.Response;
import org.concourplus.model.usersetup.Profile;
import org.concourplus.model.usersetup.Role;

public interface PrivilegesService {

	public Response<Profile> addProfile (Profile profile);
	public Response<Profile> editProfile (Profile profile);
	public Response<Profile> disableProfile (Profile profile);
	public Response<Profile> enableProfile (Profile profile);
	public List<Profile> getProfiles(Request<Profile> request);
	
	public Response<Role> addRole(Role role);
	public Response<Role> addRoleToProfile(Role role, Profile profile);
	public Response<Role> editRole(Role role);
	public Response<Role> disableRole(Role role);
	public Response<Role> enableRole(Role role);
	public List<Role> getRoles(Request<Role> request);
	
	
	
}
