package org.concourplus.service.usersetup.impl;

import org.concourplus.base.contract.Response;
import org.concourplus.dao.usersetup.ProfileRepository;
import org.concourplus.dto.usersetup.ProfileDTO;
import org.concourplus.model.usersetup.Profile;
import org.concourplus.service.mapper.EnumMapper;
import org.concourplus.service.mapper.MapperService;
import org.concourplus.service.usersetup.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private transient MapperService mapperService;

	@Override
	public Response<ProfileDTO> addUser(ProfileDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<ProfileDTO> editProfile(ProfileDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<ProfileDTO> getProfiles(ProfileDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, noRollbackFor=Exception.class)
	public ProfileDTO getProfileByCode(String code) {
		Profile profile = profileRepository.getProfileByCode(code);
		ProfileDTO profileDto = mapperService.map(profile, ProfileDTO.class, EnumMapper.MAPPING_PROFILE_ALL.toString());
		return profileDto;
	}

}
