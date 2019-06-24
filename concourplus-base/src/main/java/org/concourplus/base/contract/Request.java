package org.concourplus.base.contract;

import java.util.HashMap;
import java.util.Map;

import javax.swing.SortOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
	
	public Object getVariable(String key) {
		return variables.get(key);
	}
	
	public Map<String, Object> getVariables() {
		return variables;
	}
}
