package com.crudjava.util;

import java.util.List;

public class ResponseAPI {

	private boolean success;
	private List<MessageAPI> messages;
	private Object object;

	public ResponseAPI() {
		super();
	}

	public ResponseAPI(boolean success, Object object, List<MessageAPI> messages) {
		super();
		this.success = success;
		this.object = object;
		this.messages = messages;
	}

	public ResponseAPI(boolean success) {
		this(success, null, null);
	}

	public ResponseAPI(boolean success, Object object) {
		this(success, object, null);
	}

	public ResponseAPI(Boolean success, List<MessageAPI> listMessage) {
		this(success, null, listMessage);
	}

	// GET'S AND SET'S
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<MessageAPI> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageAPI> messages) {
		this.messages = messages;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
