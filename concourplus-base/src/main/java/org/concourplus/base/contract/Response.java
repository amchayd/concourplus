package org.concourplus.base.contract;


import java.util.ArrayList;
import java.util.List;

public class Response<T> {


	public static final String STATUS_SUCCES = "Info";

	public static final String STATUS_WARN = "Warn";

	public static final String STATUS_ERROR = "Error";

	public static final String STATUS_FATAL = "Fatal";

	private Boolean checkStatus = Boolean.FALSE;

	private String status;

	private List<String> messages;

	T model;

	/**
	 * Other field of storage if needed
	 */

	Object complement;

	public Response() {
		messages = new ArrayList<String>();
		status = STATUS_SUCCES;
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

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Object getComplement() {
		return complement;
	}

	public void setComplement(Object complement) {
		this.complement = complement;
	}

	public void addMessage(String message) {
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
	}

	public void clearMessage() {
		messages = new ArrayList<String>();
	}

	public boolean hasMessages() {
		return messages != null && !messages.isEmpty();
	}

	public boolean hasStatutError() {
		return STATUS_ERROR.equals(status);
	}

	public boolean hasStatutSucces() {
		return STATUS_SUCCES.equals(status);
	}

	public boolean hasStatutFatal() {
		return STATUS_FATAL.equals(status);
	}

	public boolean hasStatutWarn() {
		return STATUS_WARN.equals(status);
	}

	public <U> void cloneMessage(Response<U> resultat) {
		resultat.setMessages(getMessages());
		resultat.setStatus(this.status);
	}

	public <U> void addAllMessages(Response<U> resultat) {
		this.messages.addAll(resultat.getMessages());
		this.setStatus(resultat.getStatus());
	}

	public Boolean getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Boolean checkStatus) {
		this.checkStatus = checkStatus;
	}
}
