package org.concourplus.business.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonResult {

	private String status;
	private List<String> messages;
	private HashMap<String, Object> data;

	public JsonResult() {
		super();
		this.data = new HashMap<String, Object>();
		this.messages = new ArrayList<String>();
	}

	public JsonResult(String status, List<String> messages, HashMap<String, Object> data) {
		super();
		this.status = status;
		this.messages = messages;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

}
