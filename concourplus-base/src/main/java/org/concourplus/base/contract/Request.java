package org.concourplus.base.contract;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Request<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Request.class);

	private T model;

	private Map<String, Object> variables;

	private Map<String, String> variablesKeyConversion;

	private String converterId;

	private Map<Class<?>, String> converterIdByClass;

	int first;

	int pageSize;

	String sortField;

	SortOrder sortOrder;

	public Request() {
		variables = new HashMap<String, Object>();
		variablesKeyConversion = new HashMap<String, String>();
	}

	public Request(Map<String, Object> variables, T model) {
		this.model = model;
		this.variables = variables;
	}

	public Request(T model) {
		this.model = model;
		variables = new HashMap<String, Object>();
		variablesKeyConversion = new HashMap<String, String>();
	}

	public Request(Map<String, Object> variables, T model, String converterId) {
		this.model = model;
		this.variables = variables;
		this.converterId = converterId;
	}

	public Request(T model, String converterId) {
		this.model = model;
		variables = new HashMap<String, Object>();
		variablesKeyConversion = new HashMap<String, String>();
		this.converterId = converterId;
	}

	public void addVariable(String key, Object value) {
		variables.put(key, value);
	}

	public void clearVariable() {
		if (variables != null) {
			variables.clear();
		}

		if (variablesKeyConversion != null) {
			variablesKeyConversion.clear();
		}
	}

	public void removeVariable(String key) {

		variables.remove(key);

	}

	public void removeVariables(List<String> listKeys) {
		for (String key : listKeys) {
			variables.remove(key);
		}
	}
	
	public Object getVariable(String key) {
		return variables.get(key);
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getConverterId() {
		return converterId;
	}

	public void setConverterId(String converterId) {
		this.converterId = converterId;
	}
	
	public Map<String, String> getVariablesKeyConversion() {
		return variablesKeyConversion;
	}

	public void setVariablesKeyConversion(Map<String, String> variablesKeyConversion) {
		this.variablesKeyConversion = variablesKeyConversion;
	}

	public Map<Class<?>, String> getConverterIdByClass() {
		return converterIdByClass;
	}

	public void setConverterIdByClass(Map<Class<?>, String> converterIdByClass) {
		this.converterIdByClass = converterIdByClass;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public Object getGeneric(String key, Class classType) {
		if (String.class.equals(classType)) {
			return get(key);
		}
		if (Date.class.equals(classType)) {
			return getDate(key);
		}
		if (Long.class.equals(classType)) {
			return getLong(key);
		}
		if (Integer.class.equals(classType)) {
			return getInteger(key);
		}
		if (Boolean.class.equals(classType)) {
			return getBoolean(key);
		}
		if (BigDecimal.class.equals(classType)) {
			return getBigDecimal(key);
		}

		if (StringUtils.isEmpty(key)) {
			LOGGER.debug("returned null because key is null");
			return null;
		}

		Object value = variables.get(key);

		if (value == null) {
			LOGGER.debug("returned null because value is null for key : " + key);
			return null;
		}

		return value;
	}

	/**
	 * return the value as String from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {

		if (StringUtils.isEmpty(key)) {
			LOGGER.debug("returned null because key is null");
			return null;
		}

		Object value = variables.get(key);

		if (value == null) {
			LOGGER.debug("returned null because value is null for key : " + key);
			return null;
		}

		if (!(value instanceof String)) {
			LOGGER.debug("returned toString() because value is not of type String for key : " + key
					+ ", value type is : " + value.getClass().getName());
			return value.toString();
		}

		if (StringUtils.isEmpty(value)) {
			LOGGER.debug("returned null because value is empty for key : " + key);
			return null;
		}

		LOGGER.debug("returned value : " + variables.get(key) + ", for key : " + key);

		return (String) value;

	}

	/**
	 * return the value as Date from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public Date getDate(String key) {

		if (key == null) {

			LOGGER.debug("returned null because key is null");

			return null;
		}

		Object value = variables.get(key);

		if (value == null) {

			LOGGER.debug("returned null because value is null for key : " + key);

			return null;
		}

		if (value instanceof Date) {

			LOGGER.debug("returned value : " + value + ", for key : " + key);

			return (Date) value;
		}

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
			Date date = formatter.parse((String) value);

			LOGGER.debug("returned value : " + date + ", for key : " + key);

			return date;

		} catch (ParseException e) {

			LOGGER.debug(e.getMessage());
			return null;
		}

	}

	/**
	 * return the value as Long from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public Long getLong(String key) {

		if (key == null) {

			LOGGER.debug("returned null because key is null");

			return null;
		}

		Object value = variables.get(key);

		if (value == null) {

			LOGGER.debug("returned null because value is null for key : " + key);

			return null;
		}

		if (value instanceof Long) {

			return (Long) value;

		}

		try {
			Long parsedLong = Long.parseLong((String) value);

			LOGGER.debug("returned value : " + parsedLong + ", for key : " + key);

			return parsedLong;

		} catch (Exception e) {

			LOGGER.debug(e.toString());

			return null;

		}

	}

	/**
	 * return the value as Long from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {

		if (key == null) {

			LOGGER.debug("returned null because key is null");

			return null;
		}

		Object value = variables.get(key);

		if (value == null) {

			LOGGER.debug("returned null because value is null for key : " + key);

			return null;
		}

		if (value instanceof Integer) {

			return (Integer) value;

		}

		try {
			Integer parsedInteger = Integer.valueOf((String) value);

			LOGGER.debug("returned value : " + parsedInteger + ", for key : " + key);

			return parsedInteger;

		} catch (Exception e) {

			LOGGER.debug(e.getMessage());

			return null;

		}

	}

	/**
	 * return the value as BigDecimal from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public BigDecimal getBigDecimal(String key) {

		if (key == null) {

			LOGGER.debug("returned null because key is null");

			return null;
		}

		Object value = variables.get(key);

		if (value == null) {

			LOGGER.debug("returned null because value is null for key : " + key);

			return null;
		}

		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}

		try {

			Long parsedLong = Long.parseLong((String) value);

			BigDecimal parsedBig = BigDecimal.valueOf(parsedLong);

			LOGGER.debug("returned value : " + parsedBig + ", for key : " + key);

			return parsedBig;

		} catch (Exception e) {

			LOGGER.debug(e.getMessage());

			return null;

		}

	}

	/**
	 * return the value as Boolean from the variables map
	 * 
	 * @param key
	 * @return
	 */
	public Boolean getBoolean(String key) {
		if (key == null) {

			LOGGER.debug("returned null because key is null");

		}

		Object value = variables.get(key);

		if (value == null) {

			LOGGER.debug("returned null because value is null for key : " + key);

		}

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		try {

			Boolean parsedBoolean = Boolean.getBoolean((String) value);

			LOGGER.debug("returned value : " + parsedBoolean + ", for key : " + key);

			return parsedBoolean;

		} catch (Exception e) {

			LOGGER.debug(e.getMessage());

			return null;

		}
	}
}
