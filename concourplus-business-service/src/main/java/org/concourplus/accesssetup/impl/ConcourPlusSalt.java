package org.concourplus.accesssetup.impl;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

public class ConcourPlusSalt implements SaltSource{

    private static final String SALT_VALUE = "ADMIN";

	@Override
	public Object getSalt(UserDetails user) {
		return SALT_VALUE;
	}

}
