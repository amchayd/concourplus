package org.concourplus.base.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.concourplus.base.contract.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;


public abstract class SpecificationsHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpecificationsHelper.class);

	
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
	
	/*
	 * Build page for request
	 * */
	public static Pageable buildPage(Request<?> request) {
		int page = request.getPageSize() != 0 ? request.getFirst() / request.getPageSize() : 1;
		int size = request.getPageSize();
		return size > 0 ? new PageRequest(page, size) : null;
	}
	public static <T> List<Specification<T>> chooseSpecifications(Class specificationsClass, Request<?> request) {
		List<Specification<T>> specificationList = new ArrayList<Specification<T>>();
		try {
			Constructor constructor = specificationsClass.getConstructor();
			Object specificationsInstance = constructor.newInstance();

			Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(specificationsClass);

			Map<String, Object> parameters = request.getVariables();
			
			for (String methodName : parameters.keySet()) {
				if (!StringUtils.isEmpty(methodName)) {
					for (Method method : methods) {
						if (methodName.equals(method.getName())) {
								Class[] parameterTypes = method.getParameterTypes();
								if (parameterTypes == null || parameterTypes.length == 0) {
									specificationList.add((Specification<T>) method.invoke(specificationsInstance, new Object[0]));
								} else if (parameterTypes.length > 1) {
									LOGGER.info("generic mecanism does not support methods with more than one parameter, please do it manually for specification name : "
											+ methodName);
								}else {
									if (Request.class.equals(parameterTypes[0])) {
										specificationList.add((Specification<T>) method.invoke(specificationsInstance, request));
									} else {
										Object methodValue = request.getGeneric(methodName, parameterTypes[0]);
										if (methodValue != null) {
											specificationList.add((Specification<T>) method.invoke(specificationsInstance, methodValue));
										}
									}
								}
							}
						}
					}
			}
		} catch (NoSuchMethodException e) {
			LOGGER.error(null, e);
		} catch (SecurityException e) {
			LOGGER.error(null, e);
		} catch (InstantiationException e) {
			LOGGER.error(null, e);
		} catch (IllegalAccessException e) {
			LOGGER.error(null, e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(null, e);
		} catch (InvocationTargetException e) {
			LOGGER.error(null, e);
		} catch (Exception e) {
			LOGGER.error(null, e);
		}
		return specificationList;
	}
}
