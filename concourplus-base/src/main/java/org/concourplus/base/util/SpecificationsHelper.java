package org.concourplus.base.util;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public abstract class SpecificationsHelper {

	public static <T> Specifications<T> buildWhereClause(List<Specification<T>> specificationList) {

		Specifications<T> specifications = Specifications.where(null);

		/*
		 * construction of where clause 
		 */
		for (Specification<T> specification : specificationList) {
			specifications = specifications.and(specification);
		}

		return specifications;
	}
}
