package org.concourplus.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.concourplus.model.usersetup.User;
import org.concourplus.model.usersetup.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

	public static Specification<User> userNameIsEqual(final String userName) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(userRoot.get(User_.username), userName);
			}
		
		};
	}
}
