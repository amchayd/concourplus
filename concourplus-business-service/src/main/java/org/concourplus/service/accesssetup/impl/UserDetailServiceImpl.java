package org.concourplus.service.accesssetup.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.concourplus.dao.usersetup.UserRepository;
import org.concourplus.model.usersetup.User;
import org.concourplus.service.usersetup.UserSetUpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		List<String> roles = userRepository.findUserDetailService(username);

		if (roles == null) {
			roles = new ArrayList<String>(1);
		}

		final Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(roles);

		setupGrantedSuperAuthorities(username, (Collection<GrantedAuthority>) authorities);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, authorities);
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size() + 1);
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	private void setupGrantedSuperAuthorities(String username, final Collection<GrantedAuthority> authorities) {
		if (UserSetUpConstants.USER_ADMIN.equalsIgnoreCase(username)) {
			authorities.add(new SimpleGrantedAuthority(UserSetUpConstants.ROLE_ADM));
			authorities.add(new SimpleGrantedAuthority(UserSetUpConstants.ROLE_GEST_USER));
			authorities.add(new SimpleGrantedAuthority(UserSetUpConstants.ROLE_GEST_PROF));
		}
	}
}
