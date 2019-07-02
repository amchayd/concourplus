package org.concourplus.business.helpers;

import java.io.IOException;
import java.util.List;

import javax.faces.FacesException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonHelper<T> {

	private T model;
	private List<T> models;
	private String Status;
	private List<String> messages;
	private Object object;
	private String json;

	public JsonHelper() {
		super();
	}

	public JsonHelper(T model) {
		super();
		this.model = model;
	}

	public JsonHelper(T model, String Status, List<String> messages, Object object) {
		super();
		this.model = model;
		this.Status = Status;
		this.messages = messages;
		this.object = object;
	}

	public void jsonToObject() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			this.model = (T) mapper.readValue(this.json, model.getClass());
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	public void jsonToObjects() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			this.models = mapper.readValue(this.json, new TypeReference<List<T>>() {
			});
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public List<T> getModels() {
		return models;
	}

	public void setModels(List<T> models) {
		this.models = models;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
