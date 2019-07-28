package org.concourplus.service.usersetup;

import org.concourplus.base.contract.Response;
import org.concourplus.dto.usersetup.ProfileDTO;

public interface ProfileService {
	Response<ProfileDTO> addUser(ProfileDTO user);

	Response<ProfileDTO> editProfile(ProfileDTO user);

	Response<ProfileDTO> getProfiles(ProfileDTO user);
	
	public ProfileDTO getProfileByCode(String code);

}
